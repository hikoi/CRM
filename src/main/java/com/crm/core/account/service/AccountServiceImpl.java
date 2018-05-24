package com.crm.core.account.service;

import com.crm.commons.consts.CacheName;
import com.crm.core.account.dao.AccountDao;
import com.crm.core.account.dao.UserDao;
import com.crm.core.pem.dao.PemDao;
import com.crm.core.permission.consts.ResourceType;
import com.crm.core.permission.dao.FunctionDao;
import com.crm.core.permission.dao.MenuDao;
import com.crm.core.permission.dao.PermissionDao;
import com.crm.core.permission.dao.RoleDao;
import com.crm.core.permission.entity.Function;
import com.crm.core.permission.entity.Menu;
import com.crm.core.permission.entity.Permission;
import com.crm.core.permission.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.config.CacheManagementConfigUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.wah.doraemon.entity.Account;
import org.wah.doraemon.entity.User;
import org.wah.doraemon.entity.consts.Sex;
import org.wah.doraemon.security.exception.AuthenticationException;
import org.wah.doraemon.security.exception.DuplicateException;
import org.wah.doraemon.utils.ObjectUtils;
import org.wah.doraemon.utils.RSAUtils;
import org.wah.doraemon.utils.RedisUtils;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import java.util.*;

@Service
@Transactional(readOnly = true)
public class AccountServiceImpl implements AccountService{

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PermissionDao permissionDao;

    @Autowired
    private FunctionDao functionDao;

    @Autowired
    private MenuDao menuDao;

    @Autowired
    private PemDao pemDao;

    @Autowired
    private ShardedJedisPool shardedJedisPool;

    @Override
    @Transactional
    public void register(String username, String password, String name, String nickname, String headImgUrl,
                         Sex sex, String companyId, String departmentId, String positionId){
        Assert.hasText(username, "账户名不能为空");
        Assert.hasText(password, "账户密码不能为空");

        if(accountDao.existByUsername(username)){
            throw new DuplicateException("账户[{0}]已注册", username);
        }

        //新增账户
        Account account = new Account();
        account.setUsername(username);
        account.setPassword(password);
        accountDao.saveOrUpdate(account);

        //新增用户
        User user = new User();
        user.setAccountId(account.getId());
        user.setName(name);
        user.setNickname(nickname);
        user.setHeadImgUrl(headImgUrl);
        user.setSex(sex);
        userDao.saveOrUpdate(user);

        //绑定组织架构
        Permission companyPermission    = permissionDao.getByResourceId(companyId);
        Permission departmentPermission = permissionDao.getByResourceId(departmentId);
        Permission positionPermission   = permissionDao.getByResourceId(positionId);

        permissionDao.updateCompanysToAccount(Arrays.asList(companyPermission.getId()), account.getId());
        permissionDao.updateDepartmentsToAccount(Arrays.asList(departmentPermission.getId()), account.getId());
        permissionDao.updatePositionsToAccount(Arrays.asList(positionPermission.getId()), account.getId());
    }

    @Override
    public String login(String username, String password){
        Assert.hasText(username, "账户名不能为空");
        Assert.hasText(password, "账户密码不能为空");

        Account account = accountDao.getByUsername(username);
        if(account == null){
            throw new AuthenticationException("账户名或密码不正确");
        }

        //查询私钥
        String privateKey = pemDao.getPrivateKey();

        //校验密码
        if(!RSAUtils.equalsByPrivateKey(password, account.getPassword(), privateKey)){
            throw new AuthenticationException("账户名或密码不正确");
        }

        //缓存
        ShardedJedis jedis = shardedJedisPool.getResource();
        //删除登录异常列表
        RedisUtils.srem(jedis, CacheName.LOGIN_FAIL, account.getId());

        //缓存权限
        Set<Permission> permissions = new HashSet<Permission>();
        permissions.addAll(permissionDao.findByAccountId(account.getId(), null));

        List<Role> roles = roleDao.findByAccountId(account.getId());
        permissions.addAll(permissionDao.findByRoleIds(ObjectUtils.ids(roles), null));

        //所有功能
        Set<String> functionIds = new HashSet<String>();
        //所有菜单
        Set<String> menuIds = new HashSet<String>();

        for(Permission permission : permissions){
            switch(permission.getType()){
                case MENU:
                    menuIds.add(permission.getResourceId());
                    break;
                case FUNCTION:
                    functionIds.add(permission.getResourceId());
                    break;
                default:
                    break;
            }
        }

        List<Function> functions = functionDao.find(null, null, null, new ArrayList<String>(functionIds));
        List<Menu> menus = menuDao.find(null, null, null, null, null, new ArrayList<String>(menuIds));

        //缓存
        RedisUtils.sadd(jedis, CacheName.USER_FUNCTION + account.getId(), functions);
        RedisUtils.sadd(jedis, CacheName.USER_MENU + account.getId(), menus);
        RedisUtils.close(jedis);

        return account.getId();
    }
}

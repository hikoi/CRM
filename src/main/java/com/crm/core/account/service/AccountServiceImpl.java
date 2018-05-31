package com.crm.core.account.service;

import com.crm.commons.consts.CacheName;
import com.crm.commons.consts.Constants;
import com.crm.core.permission.dao.FunctionDao;
import com.crm.core.permission.dao.MenuDao;
import com.crm.core.permission.dao.PermissionDao;
import com.crm.core.permission.dao.RoleDao;
import com.crm.core.permission.entity.Function;
import com.crm.core.permission.entity.Menu;
import com.crm.core.permission.entity.Permission;
import com.crm.core.permission.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.wah.doraemon.entity.User;
import org.wah.doraemon.security.response.Responsed;
import org.wah.doraemon.utils.ObjectUtils;
import org.wah.doraemon.utils.RedisUtils;
import org.wah.ferryman.utils.AccountUtils;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AccountServiceImpl implements AccountService{

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PermissionDao permissionDao;

    @Autowired
    private FunctionDao functionDao;

    @Autowired
    private MenuDao menuDao;

    @Autowired
    private ShardedJedisPool shardedJedisPool;

    @Override
    public String login(String username, String password){
        Assert.hasText(username, "账户名不能为空");
        Assert.hasText(password, "账户密码不能为空");

        Responsed<String> tokenResponsed = AccountUtils.login(Constants.SSO_SERVER, username, password);
        Responsed<User>   userResponsed  = AccountUtils.getUser(Constants.SSO_SERVER, tokenResponsed.getResult());

        User user = userResponsed.getResult();

        //缓存
        ShardedJedis jedis = shardedJedisPool.getResource();
        //删除登录异常列表
        RedisUtils.srem(jedis, CacheName.LOGIN_FAIL, user.getAccountId());

        //缓存权限
        Set<Permission> permissions = new HashSet<Permission>();
        permissions.addAll(permissionDao.findByAccountId(user.getAccountId(), null));

        List<Role> roles = roleDao.findByAccountId(user.getAccountId());
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
        RedisUtils.sadd(jedis, CacheName.USER_FUNCTION + user.getAccountId(), functions);
        RedisUtils.sadd(jedis, CacheName.USER_MENU + user.getAccountId(), menus);
        RedisUtils.close(jedis);

        //返回Token
        return tokenResponsed.getResult();
    }
}

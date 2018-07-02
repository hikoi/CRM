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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.wah.doraemon.entity.Account;
import org.wah.doraemon.entity.User;
import org.wah.doraemon.entity.consts.Sex;
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
    public User register(String username, String password, Boolean isInternal, String name, String nickname, String headImgUrl, Sex sex) throws Exception{
        Assert.hasText(username, "账户名称不能为空");
        Assert.hasText(password, "账户密码不能为空");
        Assert.notNull(isInternal, "账户域标识不能为空");

        User user = AccountUtils.register(username, password, isInternal, name, nickname, headImgUrl, sex);

        return user;
    }

    @Override
    public String login(String username, String password) throws Exception{
        Assert.hasText(username, "账户名不能为空");
        Assert.hasText(password, "账户密码不能为空");

        String ticket = AccountUtils.login(username, password);
        User   user   = AccountUtils.getUser(ticket);

        //缓存
        ShardedJedis jedis = shardedJedisPool.getResource();
        //缓存用户信息
        RedisUtils.save(jedis, CacheName.USER_INFO + ticket, user);

        //查询权限
        Set<Permission> permissions = new HashSet<Permission>();
        permissions.addAll(permissionDao.findByAccountId(user.getAccountId(), null));

        List<Role> roles = roleDao.findByAccountId(user.getAccountId());
        if(roles != null && !roles.isEmpty()){
            permissions.addAll(permissionDao.findByRoleIds(ObjectUtils.ids(roles), null));
        }

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

        if(functionIds != null && !functionIds.isEmpty()){
            List<Function> functions = functionDao.find(null, null, null, new ArrayList<String>(functionIds));
            //缓存
            if(functions != null && !functions.isEmpty()){
                RedisUtils.sadd(jedis, CacheName.USER_FUNCTION + ticket, functions);
            }
        }

        if(menuIds != null && !menuIds.isEmpty()){
            List<Menu> menus = menuDao.find(null, null, null, null, null, new ArrayList<String>(menuIds));
            //缓存
            if(menus != null && !menus.isEmpty()){
                RedisUtils.sadd(jedis, CacheName.USER_MENU + ticket, menus);
            }
        }

        RedisUtils.close(jedis);

        //返回Ticket
        return ticket;
    }
}

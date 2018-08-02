package com.crm.core.permission.service;

import com.crm.commons.consts.CacheName;
import com.crm.core.authentication.entity.ServiceTicket;
import com.crm.core.group.dao.GroupsMenuDao;
import com.crm.core.permission.consts.ResourceType;
import com.crm.core.permission.dao.*;
import com.crm.core.permission.entity.Menu;
import com.crm.core.permission.entity.Permission;
import com.crm.core.permission.entity.Role;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.wah.doraemon.entity.consts.UsingState;
import org.wah.doraemon.security.request.Page;
import org.wah.doraemon.security.request.PageRequest;
import org.wah.doraemon.utils.ObjectUtils;
import org.wah.doraemon.utils.RedisUtils;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class MenuServiceImpl implements MenuService{

    @Autowired
    private MenuDao menuDao;

    @Autowired
    private GroupsMenuDao groupsMenuDao;

    @Autowired
    private PermissionDao permissionDao;

    @Autowired
    private AccountPermissionDao accountPermissionDao;

    @Autowired
    private RolePermissionDao rolePermissionDao;

    @Autowired
    private AccountRoleDao accountRoleDao;

    @Autowired
    private ShardedJedisPool pool;

    @Override
    @Transactional
    public void save(String groupsId, Menu menu){
        Assert.notNull(menu, "菜单信息不能为空");

        //保存
        menuDao.saveOrUpdate(menu);

        //添加权限
        Permission permission = new Permission();
        permission.setResourceId(menu.getId());
        permission.setType(ResourceType.MENU);
        permissionDao.save(permission);

        //设置分组
        if(StringUtils.isNotBlank(groupsId)){
            groupsMenuDao.save(groupsId, menu.getId());
        }
    }

    @Override
    @Transactional
    public void saveList(String groupsId, List<Menu> menus){
        Assert.notEmpty(menus, "菜单信息列表不能为空");

        //保存
        menuDao.saveList(menus);

        //权限列表
        List<Permission> permissions = new ArrayList<Permission>();
        for(Menu menu : menus){
            Permission permission = new Permission();
            permission.setResourceId(menu.getId());
            permission.setType(ResourceType.MENU);
            permissions.add(permission);
        }
        permissionDao.saveList(permissions);

        //设置分组
        if(StringUtils.isNotBlank(groupsId)){
            groupsMenuDao.saveList(groupsId, ObjectUtils.ids(menus));
        }
    }

    @Override
    @Transactional
    public void update(Menu menu){
        Assert.notNull(menu, "菜单信息不能为空");
        Assert.hasText(menu.getId(), "菜单ID不能为空");

        menuDao.saveOrUpdate(menu);
    }

    @Override
    public Menu getById(String id){
        Assert.hasText(id, "菜单ID不能为空");

        return menuDao.getById(id);
    }

    @Override
    public List<Menu> findByAccountId(String accountId){
        Assert.hasText(accountId, "账户ID不能为空");

        //查询账户菜单权限
        List<Permission> permissions = accountPermissionDao.find(Arrays.asList(accountId), ResourceType.MENU);
        //查询账户角色
        List<Role> roles = accountRoleDao.findRoles(Arrays.asList(accountId), UsingState.USABLE);
        //查询角色菜单权限
        if(roles != null && !roles.isEmpty()){
            permissions.addAll(rolePermissionDao.find(ObjectUtils.ids(roles), ResourceType.MENU, UsingState.USABLE));
        }

        if(permissions != null && !permissions.isEmpty()){
            return menuDao.find(null, null, ObjectUtils.properties(permissions, "resourceId", String.class));
        }

        return null;
    }

    @Override
    public List<String> findByTicket(String ticket){
        Assert.hasText(ticket, "票据不能为空");

        try(ShardedJedis jedis = pool.getResource()){
            //查询票据
            ServiceTicket st = RedisUtils.get(jedis, CacheName.SERVICE_TICKET + ticket, ServiceTicket.class);
            //账户ID
            String accountId = st.getAccountId();
            //查询账户菜单权限
            List<Permission> permissions = accountPermissionDao.find(Arrays.asList(accountId), ResourceType.MENU);
            //查询账户角色
            List<Role> roles = accountRoleDao.findRoles(Arrays.asList(accountId), UsingState.USABLE);
            if(roles != null && !roles.isEmpty()){
                permissions.addAll(rolePermissionDao.find(ObjectUtils.ids(roles), ResourceType.MENU, UsingState.USABLE));
            }

            if(permissions != null && !permissions.isEmpty()){
                List<Menu> list = menuDao.find(null, null, ObjectUtils.properties(permissions, "resourceId", String.class));

                return ObjectUtils.properties(list, "url", String.class);
            }

            return null;
        }
    }
}

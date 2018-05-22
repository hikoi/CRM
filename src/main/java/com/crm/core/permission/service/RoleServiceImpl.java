package com.crm.core.permission.service;

import com.crm.core.permission.dao.RoleDao;
import com.crm.core.permission.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.wah.doraemon.entity.consts.UsingState;
import org.wah.doraemon.security.request.Page;
import org.wah.doraemon.security.request.PageRequest;

@Service
@Transactional(readOnly = true)
public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleDao roleDao;

    @Override
    @Transactional(readOnly = false)
    public void save(Role role){
        Assert.notNull(role, "角色信息不能为空");

        roleDao.saveOrUpdate(role);
    }

    @Override
    @Transactional(readOnly = false)
    public void update(Role role){
        Assert.notNull(role, "角色信息不能为空");
        Assert.hasText(role.getId(), "角色ID不能为空");

        roleDao.saveOrUpdate(role);
    }

    @Override
    public Role getById(String id){
       Assert.hasText(id, "角色ID不能为空");

       return roleDao.getById(id);
    }

    @Override
    public Page<Role> page(PageRequest pageRequest, String id, String name, UsingState state){
        Assert.notNull(pageRequest, "分页信息不能为空");

        return roleDao.page(pageRequest, id, name, state);
    }
}

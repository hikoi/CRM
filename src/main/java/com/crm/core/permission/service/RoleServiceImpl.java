package com.crm.core.permission.service;

import com.crm.core.permission.dao.RoleDao;
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

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleDao roleDao;

    @Override
    @Transactional
    public void save(Role role){
        Assert.notNull(role, "角色信息不能为空");

        roleDao.saveOrUpdate(role);
    }

    @Override
    @Transactional
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
}

package com.crm.core.account.service;

import com.crm.core.account.dao.RoleDao;
import com.crm.core.account.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

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
    public void updateRelationByRoleId(String roleId, List<String> permissionIds){
        Assert.hasText(roleId, "角色ID不能为空");

        roleDao.updateRelationByRoleId(roleId, permissionIds);
    }

    @Override
    @Transactional(readOnly = false)
    public void updateRelationByAccountId(String accountId, List<String> roleIds){
        Assert.hasText(accountId, "账户ID不能为空");

        roleDao.updateRelationByAccountId(accountId, roleIds);
    }
}

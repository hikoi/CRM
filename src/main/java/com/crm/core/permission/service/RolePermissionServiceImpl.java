package com.crm.core.permission.service;

import com.crm.core.permission.dao.RolePermissionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class RolePermissionServiceImpl implements RolePermissionService{

    @Autowired
    private RolePermissionDao rolePermissionDao;

    @Override
    @Transactional
    public void save(String roleId, String permissionId){
        Assert.hasText(roleId, "角色ID不能为空");
        Assert.hasText(permissionId, "权限ID不能为空");

        rolePermissionDao.save(roleId, permissionId);
    }

    @Override
    @Transactional
    public void saveList(String roleId, List<String> permissionIds){
        Assert.hasText(roleId, "角色ID不能为空");
        Assert.notEmpty(permissionIds, "权限ID列表不能为空");

        rolePermissionDao.saveList(roleId, permissionIds);
    }
}

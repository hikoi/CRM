package com.crm.core.account.service;

import com.crm.core.account.dao.mapper.PermissionDao;
import com.crm.core.account.entity.Permission;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
@Transactional(readOnly = true)
public class PermissionServiceImpl implements PermissionService{

    private PermissionDao permissionDao;

    @Override
    @Transactional(readOnly = false)
    public void save(Permission permission){
        Assert.notNull(permission, "权限信息不能为空");
        Assert.hasText(permission.getUrl(), "权限路径不能为空");

        permissionDao.saveOrUpdate(permission);
    }
}

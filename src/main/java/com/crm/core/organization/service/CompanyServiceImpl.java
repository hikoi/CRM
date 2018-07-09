package com.crm.core.organization.service;

import com.crm.core.organization.dao.CompanyDao;
import com.crm.core.organization.entity.Company;
import com.crm.core.permission.consts.ResourceType;
import com.crm.core.permission.dao.PermissionDao;
import com.crm.core.permission.entity.Permission;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.wah.doraemon.security.request.Page;
import org.wah.doraemon.security.request.PageRequest;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class CompanyServiceImpl implements CompanyService{

    @Autowired
    private CompanyDao companyDao;

    @Autowired
    private PermissionDao permissionDao;

    @Override
    @Transactional
    public void save(Company company){
        Assert.notNull(company, "公司信息不能为空");
        //保存公司信息
        companyDao.saveOrUpdate(company);

        //添加资源信息
        Permission permission = new Permission();
        permission.setResourceId(company.getId());
        permission.setType(ResourceType.COMPANY);
        permissionDao.save(permission);
    }

    @Override
    @Transactional
    public void update(Company company){
        Assert.notNull(company, "公司信息不能为空");
        Assert.hasText(company.getId(), "公司ID不能为空");

        companyDao.saveOrUpdate(company);
    }

    @Override
    public Company getById(String id){
        Assert.hasText(id, "公司ID不能为空");

        return companyDao.getById(id);
    }
}

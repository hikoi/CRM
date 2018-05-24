package com.crm.core.organization.service;

import com.crm.core.organization.dao.CompanyDao;
import com.crm.core.organization.entity.Company;
import com.crm.core.permission.consts.ResourceType;
import com.crm.core.permission.dao.PermissionDao;
import com.crm.core.permission.dao.RoleDao;
import com.crm.core.permission.entity.Permission;
import com.crm.core.permission.entity.Role;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.wah.doraemon.security.request.Page;
import org.wah.doraemon.security.request.PageRequest;
import org.wah.doraemon.utils.ObjectUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional(readOnly = true)
public class CompanyServiceImpl implements CompanyService{

    @Autowired
    private CompanyDao companyDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PermissionDao permissionDao;

    @Override
    @Transactional(readOnly = false)
    public void save(Company company){
        Assert.notNull(company, "公司信息不能为空");

        //创建公司
        companyDao.saveOrUpdate(company);
        //创建资源
        Permission permission = new Permission();
        permission.setResourceId(company.getId());
        permission.setType(ResourceType.COMPANY);
        permissionDao.save(permission);
        //分配到管理员
        List<Role> admins = roleDao.findAdmins();
        permissionDao.saveResourceToRoles(permission.getId(), ObjectUtils.ids(admins));
    }

    @Override
    @Transactional(readOnly = false)
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

    @Override
    public List<Company> find(String id, String name, String address, String phone, String accountId){

        //公司ID列表
        List<String> ids = new ArrayList<String>();

        //查询资源
        if(StringUtils.isNotBlank(accountId)){
            ids.addAll(permissionDao.findResourceIdsByAccountId(accountId, ResourceType.COMPANY));
        }

        return companyDao.find(id, name, address, phone, new ArrayList(ids));
    }

    @Override
    public Page<Company> page(PageRequest pageRequest, String id, String name, String address, String phone, String accountId){
        Assert.notNull(pageRequest, "分页信息不能为空");

        //公司ID列表
        List<String> ids = new ArrayList<String>();

        //查询资源
        if(StringUtils.isNotBlank(accountId)){
            ids.addAll(permissionDao.findResourceIdsByAccountId(accountId, ResourceType.COMPANY));
        }

        return companyDao.page(pageRequest, id, name, address, phone, ids);
    }
}

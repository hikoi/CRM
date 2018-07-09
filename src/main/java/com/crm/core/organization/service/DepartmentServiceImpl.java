package com.crm.core.organization.service;

import com.crm.core.organization.dao.DepartmentDao;
import com.crm.core.organization.entity.Department;
import com.crm.core.permission.consts.ResourceType;
import com.crm.core.permission.dao.PermissionDao;
import com.crm.core.permission.entity.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.wah.doraemon.security.request.Page;
import org.wah.doraemon.security.request.PageRequest;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class DepartmentServiceImpl implements DepartmentService{

    @Autowired
    private DepartmentDao departmentDao;

    @Autowired
    private PermissionDao permissionDao;

    @Override
    @Transactional
    public void save(Department department){
        Assert.notNull(department, "部门信息不能为空");
        Assert.hasText(department.getCompanyId(), "公司ID不能为空");
        //保存部门信息
        departmentDao.saveOrUpdate(department);

        //添加资源信息
        Permission permission = new Permission();
        permission.setResourceId(department.getId());
        permission.setType(ResourceType.DEPARTMENT);
        permissionDao.save(permission);
    }

    @Override
    @Transactional
    public void update(Department department){
        Assert.notNull(department, "部门信息不能为空");
        Assert.hasText(department.getId(), "部门ID不能为空");

        departmentDao.saveOrUpdate(department);
    }

    @Override
    public Department getById(String id){
        Assert.hasText(id, "部门ID不能为空");

        return departmentDao.getById(id);
    }

    @Override
    public List<Department> find(String name, String companyId){
        return departmentDao.find(name, companyId);
    }

    @Override
    public Page<Department> page(PageRequest pageRequest, String name, String companyId){
        Assert.notNull(pageRequest, "分页信息不能为空");

        return departmentDao.page(pageRequest, name, companyId);
    }
}

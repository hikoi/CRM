package com.crm.core.organization.service;

import com.crm.core.organization.entity.Department;
import org.wah.doraemon.security.request.Page;
import org.wah.doraemon.security.request.PageRequest;

public interface DepartmentService{

    void save(Department department);

    void update(Department department);

    Department getById(String id);

    Page<Department> page(PageRequest pageRequest, String id, String name, String companyId);
}
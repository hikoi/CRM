package com.crm.core.organization.service;

import com.crm.core.organization.entity.Department;
import org.wah.doraemon.security.request.Page;
import org.wah.doraemon.security.request.PageRequest;

import java.util.List;

public interface DepartmentService{

    void save(Department department);

    void update(Department department);

    Department getById(String id);

    List<Department> find(String name, String companyId);

    Page<Department> page(PageRequest pageRequest, String name, String companyId);
}

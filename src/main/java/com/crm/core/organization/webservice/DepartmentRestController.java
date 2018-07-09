package com.crm.core.organization.webservice;

import com.crm.core.organization.entity.Department;
import com.crm.core.organization.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.wah.doraemon.security.request.Page;
import org.wah.doraemon.security.request.PageRequest;
import org.wah.doraemon.security.response.Responsed;

import java.util.List;

@RestController
@RequestMapping(value = "/api/1.0/department")
public class DepartmentRestController{

    @Autowired
    private DepartmentService departmentService;

    @RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Responsed<Department> save(@RequestBody Department department){
        departmentService.save(department);

        return new Responsed<Department>("保存成功", department);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Responsed<Department> update(@RequestBody Department department){
        departmentService.update(department);

        return new Responsed<Department>("更新成功", department);
    }

    @RequestMapping(value = "/find", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Responsed<List<Department>> find(String companyId, String name){
        List<Department> list = departmentService.find(name, companyId);

        return new Responsed<List<Department>>("查询成功", list);
    }
}

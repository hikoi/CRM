package com.crm.core.permission.webservice;

import com.crm.core.permission.entity.Role;
import com.crm.core.permission.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.wah.doraemon.entity.consts.UsingState;
import org.wah.doraemon.security.request.Page;
import org.wah.doraemon.security.request.PageRequest;
import org.wah.doraemon.security.response.Responsed;

@RestController
@RequestMapping(value = "/api/1.0/role")
public class RoleRestController{

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Responsed<Role> save(@RequestBody Role role){
        roleService.save(role);

        return new Responsed<Role>("保存成功", role);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Responsed<Role> update(@RequestBody Role role){
        roleService.update(role);

        return new Responsed<Role>("更新成功", role);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Responsed<Role> getById(@PathVariable("id") String id){
        Role function = roleService.getById(id);

        return new Responsed<Role>("查询成功", function);
    }
}

package com.crm.core.permission.webservice;

import com.crm.core.permission.service.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.wah.doraemon.security.response.Responsed;

import java.util.List;

@RestController
@RequestMapping(value = "/api/1.0/role/permission")
public class RolePermissionRestController{

    @Autowired
    private RolePermissionService rolePermissionService;

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Responsed save(String roleId, String permissionId){
        rolePermissionService.save(roleId, permissionId);

        return new Responsed("保存成功");
    }

    @RequestMapping(value = "/{roleId}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Responsed saveList(@PathVariable("roleId") String roleId, @RequestBody List<String> permissionIds){
        rolePermissionService.saveList(roleId, permissionIds);

        return new Responsed("保存成功");
    }
}

package com.crm.core.permission.webservice;

import com.crm.core.permission.entity.Menu;
import com.crm.core.permission.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.wah.doraemon.security.request.Page;
import org.wah.doraemon.security.request.PageRequest;
import org.wah.doraemon.security.response.Responsed;

@RestController
@RequestMapping(value = "/api/1.0/menu")
public class MenuRestController{

    @Autowired
    private MenuService menuService;

    @RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Responsed<Menu> save(@RequestBody Menu menu){
        menuService.save(menu);

        return new Responsed<Menu>("保存成功", menu);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Responsed<Menu> update(@RequestBody Menu menu){
        menuService.update(menu);

        return new Responsed<Menu>("更新成功", menu);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Responsed<Menu> getById(@PathVariable("id") String id){
        Menu menu = menuService.getById(id);

        return new Responsed<Menu>("查询成功", menu);
    }

    @RequestMapping(value = "/page", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Responsed<Page<Menu>> page(Long pageNum, Long pageSize, String id, String name, String url, String parentId, Boolean isParent){
        PageRequest pageRequest = new PageRequest(pageNum, pageSize);
        Page<Menu> page = menuService.page(pageRequest, id, name, url, parentId, isParent);

        return new Responsed<Page<Menu>>("查询成功", page);
    }
}

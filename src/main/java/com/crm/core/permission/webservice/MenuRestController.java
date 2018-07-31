package com.crm.core.permission.webservice;

import com.crm.commons.consts.HeaderName;
import com.crm.core.permission.entity.Menu;
import com.crm.core.permission.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.wah.doraemon.security.response.Responsed;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/api/1.0/menu")
public class MenuRestController{

    @Autowired
    private MenuService menuService;

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

    @RequestMapping(value = "/ticket", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Responsed<List<String>> findByTicket(HttpServletRequest request){
        String ticket = request.getHeader(HeaderName.TICKET);
        List<String> list = menuService.findByTicket(ticket);

        return new Responsed<List<String>>("查询成功", list);
    }
}

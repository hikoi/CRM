package com.crm.core.group.webservice;

import com.crm.core.group.entity.GroupsMenu;
import com.crm.core.group.service.GroupsMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.wah.doraemon.security.response.Responsed;

import java.util.List;

@RestController
@RequestMapping(value = "/api/1.0/groups/menu")
public class GroupsMenuRestController{

    @Autowired
    private GroupsMenuService groupsMenuService;

    @RequestMapping(value = "/find", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Responsed<List<GroupsMenu>> find(String groupsId, String menuId){
        List<GroupsMenu> list = groupsMenuService.find(groupsId, menuId);

        return new Responsed<List<GroupsMenu>>("查询成功", list);
    }
}

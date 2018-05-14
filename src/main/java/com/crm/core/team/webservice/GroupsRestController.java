package com.crm.core.team.webservice;

import com.crm.core.team.entity.Groups;
import com.crm.core.team.service.GroupsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.wah.doraemon.entity.consts.UsingState;
import org.wah.doraemon.security.request.Page;
import org.wah.doraemon.security.request.PageRequest;
import org.wah.doraemon.security.response.Responsed;

@RestController
@RequestMapping(value = "/api/1.0/groups")
public class GroupsRestController{

    @Autowired
    private GroupsService groupsService;

    @RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Responsed<Groups> save(@RequestBody Groups group){
        groupsService.save(group);

        return new Responsed<Groups>("保存成功", group);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Responsed<Groups> update(@RequestBody Groups group){
        groupsService.update(group);

        return new Responsed<Groups>("更新成功", group);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Responsed<Groups> getById(@PathVariable("id") String id){
        Groups group = groupsService.getById(id);

        return new Responsed<Groups>("查询成功", group);
    }

    @RequestMapping(value = "/page", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Responsed<Page<Groups>> page(Long pageNum, Long pageSize, String id, String name, UsingState state){
        PageRequest pageRequest = new PageRequest(pageNum, pageSize);
        Page<Groups> page = groupsService.page(pageRequest, id, name, state);

        return new Responsed<Page<Groups>>("查询成功", page);
    }
}

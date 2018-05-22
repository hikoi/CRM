package com.crm.core.group.webservice;

import com.crm.core.group.entity.Groups;
import com.crm.core.group.service.GroupsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.wah.doraemon.entity.consts.UsingState;
import org.wah.doraemon.security.request.Page;
import org.wah.doraemon.security.request.PageRequest;
import org.wah.doraemon.security.response.Responsed;

import java.util.List;

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

    @RequestMapping(value = "/relation/group/{groupId}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Responsed updateRelationByGroupId(@PathVariable("groupId") String groupId, List<String> wechatIds){
        groupsService.updateRelationByGroupId(groupId, wechatIds);

        return new Responsed("更新成功");
    }

    @RequestMapping(value = "/relation/wechat/{wechatId}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Responsed updateRelationByWechatId(@PathVariable("wechatId") String wechatId, List<String> groupIds){
        groupsService.updateRelationByWechatId(wechatId, groupIds);

        return new Responsed("更新成功");
    }
}

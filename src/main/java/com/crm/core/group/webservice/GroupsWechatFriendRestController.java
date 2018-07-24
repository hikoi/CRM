package com.crm.core.group.webservice;

import com.crm.commons.consts.HeaderName;
import com.crm.core.group.service.GroupsWechatFriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.wah.doraemon.security.response.Responsed;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/1.0/groups/wechatFriend")
public class GroupsWechatFriendRestController{

    @Autowired
    private GroupsWechatFriendService groupsWechatFriendService;

    @RequestMapping(value = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public Responsed update(String newGroupsId, String oldGroupsId, String friendId){
        groupsWechatFriendService.save(newGroupsId, oldGroupsId, friendId);

        return new Responsed("更新成功");
    }

    @RequestMapping(value = "/find/ticket", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Responsed<List<Map<String, Object>>> findByTicket(HttpServletRequest request){
        String ticket = request.getHeader(HeaderName.TICKET);
        List<Map<String, Object>> list = groupsWechatFriendService.findByTicket(ticket);

        return new Responsed<List<Map<String, Object>>>("查询成功", list);
    }
}

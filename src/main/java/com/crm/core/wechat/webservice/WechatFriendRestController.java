package com.crm.core.wechat.webservice;

import com.crm.core.wechat.entity.WechatFriend;
import com.crm.core.wechat.service.WechatFriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.wah.doraemon.security.request.Page;
import org.wah.doraemon.security.request.PageRequest;
import org.wah.doraemon.security.response.Responsed;

import java.util.List;

@RestController
@RequestMapping(value = "/api/1.0/wechatFriend")
public class WechatFriendRestController{

    @Autowired
    private WechatFriendService wechatFriendService;

    @RequestMapping(value = "/{wxno}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Responsed save(@PathVariable("wxno") String wxno, @RequestBody WechatFriend friend){
        wechatFriendService.save(wxno, friend);

        return new Responsed("保存成功");
    }

    @RequestMapping(value = "/synchronize/{wxno}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Responsed synchronize(@PathVariable("wxno") String wxno, @RequestBody List<WechatFriend> friends){
        wechatFriendService.synchronize(wxno, friends);

        return new Responsed("同步成功");
    }

    @RequestMapping(value = "/page", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Responsed<Page<WechatFriend>> page(Long pageNum, Long pageSize, String sellerId, String wechatId, String wxid,
                                              String wxno, String nickname){
        PageRequest pageRequest = new PageRequest(pageNum, pageSize);
        Page<WechatFriend> page = wechatFriendService.page(pageRequest, sellerId, wechatId, wxid, wxno, nickname);

        return new Responsed<Page<WechatFriend>>("查询成功", page);
    }
}

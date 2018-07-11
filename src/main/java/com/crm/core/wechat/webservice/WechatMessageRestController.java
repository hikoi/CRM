package com.crm.core.wechat.webservice;

import com.crm.core.wechat.consts.WechatMessageStatus;
import com.crm.core.wechat.consts.WechatMessageType;
import com.crm.core.wechat.entity.WechatMessage;
import com.crm.core.wechat.service.WechatMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.wah.doraemon.security.request.Page;
import org.wah.doraemon.security.request.PageRequest;
import org.wah.doraemon.security.response.Responsed;

import java.util.List;

@RestController
@RequestMapping(value = "/api/1.0/wechatMessage")
public class WechatMessageRestController{

    @Autowired
    private WechatMessageService wechatMessageService;

    @RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Responsed save(@RequestBody WechatMessage message){
        wechatMessageService.save(message);

        return new Responsed("保存成功");
    }

    @RequestMapping(value = "/synchronize", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Responsed synchronize(@RequestBody List<WechatMessage> messages){
        wechatMessageService.synchronize(messages);

        return new Responsed("保存成功");
    }

    @RequestMapping(value = "/page", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Responsed<Page<WechatMessage>> page(Long pageNum, Long pageSize, String accountId, String wechatId,
                                               String wxid, WechatMessageType type, WechatMessageStatus status){
        PageRequest pageRequest = new PageRequest(pageNum, pageSize);
        Page<WechatMessage> page = wechatMessageService.page(pageRequest, accountId, wechatId, wxid, type, status);

        return new Responsed<Page<WechatMessage>>("查询成功", page);
    }
}

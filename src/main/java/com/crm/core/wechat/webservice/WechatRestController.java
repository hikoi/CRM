package com.crm.core.wechat.webservice;

import com.crm.core.wechat.entity.Wechat;
import com.crm.core.wechat.service.WechatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.wah.doraemon.security.request.Page;
import org.wah.doraemon.security.request.PageRequest;
import org.wah.doraemon.security.response.Responsed;

@RestController
@RequestMapping(value = "/api/1.0/wechat")
public class WechatRestController{

    @Autowired
    private WechatService wechatService;

    @RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Responsed<Wechat> save(@RequestBody Wechat wechat){
        wechatService.save(wechat);

        return new Responsed<Wechat>("保存成功", wechat);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Responsed<Wechat> update(@RequestBody Wechat wechat){
        wechatService.update(wechat);

        return new Responsed<Wechat>("更新成功", wechat);
    }
}

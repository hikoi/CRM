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

    @RequestMapping(value = "/{wxno}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Responsed<Wechat> getByWxno(@PathVariable("wxno") String wxno, String registrationId){
        Wechat wechat = wechatService.getByWxno(wxno, registrationId);

        return new Responsed<Wechat>("查询成功", wechat);
    }

    @RequestMapping(value = "/page", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Responsed<Page<Wechat>> page(Long pageNum, Long pageSize, String accountId, String wxno, String nickname){
        PageRequest pageRequest = new PageRequest(pageNum, pageSize);
        Page<Wechat> page = wechatService.page(pageRequest, accountId, wxno, nickname);

        return new Responsed<Page<Wechat>>("查询成功", page);
    }

    @RequestMapping(value = "/page/{accountId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Responsed<Page<Wechat>> pageByAccountId(Long pageNum, Long pageSize, @PathVariable("accountId") String accountId, String wxno, String nickname){
        PageRequest pageRequest = new PageRequest(pageNum, pageSize);
        Page<Wechat> page = wechatService.page(pageRequest, accountId, wxno, nickname);

        return new Responsed<Page<Wechat>>("查询成功", page);
    }
}

package com.crm.core.jpush.webservice;

import com.crm.core.jpush.service.JPushService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.wah.doraemon.security.response.Responsed;

@RestController
@RequestMapping(value = "/api/1.0/jpush")
public class JPushRestController{

    @Autowired
    private JPushService jPushService;

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Responsed save(String wxno, String registrationId){
        jPushService.save(wxno, registrationId);

        return new Responsed("保存成功");
    }
}

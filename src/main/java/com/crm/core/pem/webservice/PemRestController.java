package com.crm.core.pem.webservice;

import com.crm.core.pem.service.PemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.wah.doraemon.security.response.Responsed;

@RestController
@RequestMapping(value = "/api/1.0/pem")
public class PemRestController{

    @Autowired
    private PemService pemService;

    @RequestMapping(value = "/publicKey", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Responsed<String> getPublicKey(){
        String publicKey = pemService.getPublicKey();

        return new Responsed<String>("查询成功", publicKey);
    }
}

package com.crm.core.account.webservice;

import com.crm.core.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.wah.doraemon.security.response.Responsed;
import org.wah.ferryman.security.consts.HttpHeaderName;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/api/1.0/account")
public class AccountRestController{

    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Responsed<String> login(HttpServletResponse response, String username, String password){
        String token = accountService.login(username, password);

        response.setHeader(HttpHeaderName.AUTHORIZATION, token);

        return new Responsed<String>("登录成功", token);
    }
}

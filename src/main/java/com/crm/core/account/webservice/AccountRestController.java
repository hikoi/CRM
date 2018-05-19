package com.crm.core.account.webservice;

import com.crm.commons.consts.SessionName;
import com.crm.core.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.wah.doraemon.entity.Account;
import org.wah.doraemon.security.response.Responsed;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/api/1.0/account")
public class AccountRestController{

    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Responsed register(String username, String password){
        accountService.register(username, password);

        return new Responsed("注册成功");
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Responsed<String> login(HttpServletRequest request, String username, String password){
        Account account = accountService.login(username, password);
        request.getSession().setAttribute(SessionName.ACCOUNT_ID, account.getId());

        return new Responsed("登录成功", account.getId());
    }
}

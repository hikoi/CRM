package com.crm.core.account.webservice;

import com.crm.core.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.wah.doraemon.entity.consts.Sex;
import org.wah.doraemon.security.response.Responsed;

@RestController
@RequestMapping(value = "/api/1.0/account")
public class AccountRestController{

    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Responsed save(String username, String password, String nickname, String name, Sex sex, String companyId,
                              String departmentId, String positionId){

        accountService.save(username, password, nickname, name, sex, companyId, departmentId, positionId);

        return new Responsed("保存成功");
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Responsed<String> login(String username, String password){
        String ticket = accountService.login(username, password);

        return new Responsed<String>("登录成功", ticket);
    }
}

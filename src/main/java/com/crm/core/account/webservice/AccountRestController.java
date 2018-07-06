package com.crm.core.account.webservice;

import com.crm.core.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/1.0/account")
public class AccountRestController{

    @Autowired
    private AccountService accountService;

}

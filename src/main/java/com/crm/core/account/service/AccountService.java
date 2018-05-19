package com.crm.core.account.service;

import org.wah.doraemon.entity.Account;

public interface AccountService{

    void register(String username, String password);

    Account login(String username, String password);
}

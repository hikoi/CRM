package com.crm.core.account.service;

import org.wah.doraemon.entity.Account;

public interface AccountService{

    void register(Account account);

    Account login(String username, String password);
}

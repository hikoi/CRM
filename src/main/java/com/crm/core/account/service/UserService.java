package com.crm.core.account.service;

import org.wah.doraemon.entity.User;

public interface UserService{

    User getByTicket(String ticket);
}

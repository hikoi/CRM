package com.crm.core.account.service;

import org.wah.doraemon.entity.User;

public interface UserService{

    void save(User user);

    void update(User user);
}

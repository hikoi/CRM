package com.crm.core.account.service;

import org.wah.doraemon.entity.User;
import org.wah.doraemon.entity.consts.Sex;
import org.wah.doraemon.security.request.Page;
import org.wah.doraemon.security.request.PageRequest;

public interface UserService{

    void save(User user);

    void update(User user);

    User getByAccountId(String accountId);

    Page<User> page(PageRequest pageRequest, String accountId, String name, String nickname, Sex sex);
}

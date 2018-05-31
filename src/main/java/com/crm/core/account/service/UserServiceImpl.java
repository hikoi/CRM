package com.crm.core.account.service;

import com.crm.commons.consts.Constants;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.wah.doraemon.entity.User;
import org.wah.doraemon.security.response.Responsed;
import org.wah.ferryman.utils.AccountUtils;

@Service
public class UserServiceImpl implements UserService{

    @Override
    public User getByToken(String token){
        Assert.hasText(token, "票据凭证不能为空");

        Responsed<User> responsed = AccountUtils.getUser(Constants.SSO_SERVER, token);

        return responsed.getResult();
    }
}

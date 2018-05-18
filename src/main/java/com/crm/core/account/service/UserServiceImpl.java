package com.crm.core.account.service;

import com.crm.core.account.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.wah.doraemon.entity.User;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

    @Override
    @Transactional(readOnly = false)
    public void save(User user){
        Assert.notNull(user, "用户信息不能为空");
        Assert.hasText(user.getAccountId(), "账户ID不能为空");

        userDao.saveOrUpdate(user);
    }

    @Override
    @Transactional(readOnly = false)
    public void update(User user){
        Assert.notNull(user, "用户信息不能为空");
        Assert.hasText(user.getId(), "用户ID不能为空");

        userDao.saveOrUpdate(user);
    }
}

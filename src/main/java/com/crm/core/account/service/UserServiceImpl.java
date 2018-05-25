package com.crm.core.account.service;

import com.crm.core.account.dao.UserDao;
import com.crm.core.permission.consts.ResourceType;
import com.crm.core.permission.dao.PermissionDao;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.wah.doraemon.entity.User;
import org.wah.doraemon.entity.consts.Sex;
import org.wah.doraemon.security.request.Page;
import org.wah.doraemon.security.request.PageRequest;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

    @Autowired
    private PermissionDao permissionDao;

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

    @Override
    public User getByAccountId(String accountId){
        Assert.hasText(accountId, "账户ID不能为空");

        return userDao.getByAccount(accountId);
    }

    @Override
    public Page<User> page(PageRequest pageRequest, String accountId, String name, String nickname,
                           Sex sex, String companyId, String departmentId, String positionId){
        Assert.notNull(pageRequest, "分页信息不能为空");

        //用户ID列表
        List<String> ids = new ArrayList<String>();

        if(StringUtils.isNotBlank(accountId)){
            if(StringUtils.isNotBlank(companyId)){
                ids.addAll(permissionDao.findAccountIdsByResourceId(companyId));
            }
            if(StringUtils.isNotBlank(departmentId)){
                ids.retainAll(permissionDao.findAccountIdsByResourceId(departmentId));
            }
            if(StringUtils.isNotBlank(positionId)){
                ids.retainAll(permissionDao.findAccountIdsByResourceId(positionId));
            }
        }

        return userDao.page(pageRequest, accountId, name, nickname, sex, ids);
    }
}

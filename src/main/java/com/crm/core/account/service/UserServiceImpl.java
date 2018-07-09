package com.crm.core.account.service;

import com.crm.core.account.dao.UserDao;
import com.crm.core.permission.dao.PermissionDao;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.wah.doraemon.entity.User;
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
    public Page<User> page(PageRequest pageRequest, String username, String name, String companyId, String departmentId,
                           String positionId){
        Assert.notNull(pageRequest, "分页信息不能为空");

        //账户ID
        List<String> accountIds = new ArrayList<String>();
        //查询账户ID
        if(StringUtils.isNotBlank(companyId)){
            accountIds.addAll(permissionDao.findAccountIdsByResourceId(companyId));
        }
        if(StringUtils.isNotBlank(departmentId)){
            accountIds.addAll(permissionDao.findAccountIdsByResourceId(departmentId));
        }
        if(StringUtils.isNotBlank(positionId)){
            permissionDao.findAccountIdsByResourceId(positionId);
        }

        return userDao.pageWithAccount(pageRequest, name, username, true, accountIds);
    }
}

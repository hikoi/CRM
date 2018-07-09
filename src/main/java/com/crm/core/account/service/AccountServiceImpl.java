package com.crm.core.account.service;

import com.crm.core.account.dao.AccountDao;
import com.crm.core.account.dao.UserDao;
import com.crm.core.permission.dao.PermissionDao;
import com.crm.core.permission.entity.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.wah.doraemon.entity.Account;
import org.wah.doraemon.entity.User;
import org.wah.doraemon.entity.consts.AccountState;
import org.wah.doraemon.entity.consts.Sex;
import org.wah.doraemon.security.exception.DuplicateException;

import java.util.Arrays;

@Service
@Transactional(readOnly = true)
public class AccountServiceImpl implements AccountService{

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private PermissionDao permissionDao;

    @Transactional
    @Override
    public void save(String username, String password, String nickname, String name, Sex sex, String companyId,
                     String departmentId, String positionId){

        Assert.hasText(username, "账户登录名不能为空");
        Assert.hasText(password, "账户密码不能为空");
        Assert.hasText(name, "用户名称不能为空");
        Assert.hasText(companyId, "公司ID不能为空");
        Assert.hasText(departmentId, "部门ID不能为空");
        Assert.hasText(positionId, "岗位ID不能为空");

        //创建账户
        if(accountDao.existByUsername(username)){
            throw new DuplicateException("账户[{0}]以注册", username);
        }
        Account account = new Account();
        account.setUsername(username);
        account.setPassword(password);
        account.setIsInternal(true);
        account.setState(AccountState.NORMAL);
        accountDao.saveOrUpdate(account);

        //创建用户
        User user = new User();
        user.setAccountId(account.getId());
        user.setNickname(nickname);
        user.setName(name);
        user.setSex(sex);
        userDao.saveOrUpdate(user);

        //关联组织架构
        Permission company = permissionDao.getByResourceId(companyId);
        permissionDao.updateCompanysToAccount(Arrays.asList(company.getId()), account.getId());

        Permission department = permissionDao.getByResourceId(departmentId);
        permissionDao.updateDepartmentsToAccount(Arrays.asList(department.getId()), account.getId());

        Permission position = permissionDao.getByResourceId(positionId);
        permissionDao.updatePositionsToAccount(Arrays.asList(position.getId()), account.getId());
    }
}

package com.crm.core.account.service;

import com.crm.core.account.dao.AccountDao;
import com.crm.core.account.dao.UserDao;
import com.crm.core.authentication.dao.ServiceTicketDao;
import com.crm.core.authentication.entity.ServiceTicket;
import com.crm.core.pem.dao.PemDao;
import com.crm.core.permission.consts.ResourceType;
import com.crm.core.permission.dao.AccountPermissionDao;
import com.crm.core.permission.dao.PermissionDao;
import com.crm.core.permission.entity.Permission;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.wah.doraemon.entity.Account;
import org.wah.doraemon.entity.User;
import org.wah.doraemon.entity.consts.AccountState;
import org.wah.doraemon.entity.consts.Sex;
import org.wah.doraemon.security.exception.AuthenticationException;
import org.wah.doraemon.security.exception.DuplicateException;
import org.wah.doraemon.utils.RSAUtils;

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

    @Autowired
    private AccountPermissionDao accountPermissionDao;

    @Autowired
    private PemDao pemDao;

    @Autowired
    private ServiceTicketDao serviceTicketDao;

    @Transactional
    @Override
    public void save(String username, String password, String nickname, String name, Sex sex, String companyId,
                     String departmentId, String positionId){

        Assert.hasText(username, "账户登录名不能为空");
        Assert.hasText(password, "账户密码不能为空");
        Assert.hasText(name, "用户名称不能为空");

        //创建账户
        if(accountDao.existByUsername(username)){
            throw new DuplicateException("账户[{0}]以注册", username);
        }

        Account account = new Account();
        account.setUsername(username);
        account.setPassword(password);
        account.setState(AccountState.NORMAL);
        accountDao.saveOrUpdate(account);

        //创建用户
        User user = new User();
        user.setAccountId(account.getId());
        user.setNickname(nickname);
        user.setName(name);
        user.setSex(sex);
        userDao.saveOrUpdate(user);

        //添加权限
        if(StringUtils.isNotBlank(companyId)){
            Permission permission = permissionDao.getByResourceIdAndType(companyId, ResourceType.COMPANY);
            accountPermissionDao.save(account.getId(), permission.getId());
        }
        if(StringUtils.isNotBlank(departmentId)){
            Permission permission = permissionDao.getByResourceIdAndType(departmentId, ResourceType.DEPARTMENT);
            accountPermissionDao.save(account.getId(), permission.getId());
        }
        if(StringUtils.isNotBlank(positionId)){
            Permission permission = permissionDao.getByResourceIdAndType(positionId, ResourceType.POSITION);
            accountPermissionDao.save(account.getId(), permission.getId());
        }
    }

    @Override
    public String login(String username, String password){
        Assert.hasText(username, "账户登录名不能为空");
        Assert.hasText(password, "账户登录密码不能为空");

        Account account = accountDao.getByUsername(username);
        //验证账户是否存在
        if(account == null){
            throw new AuthenticationException("账户或密码不正确");
        }

        String privateKey = pemDao.getPrivateKey();
        //验证密码
        if(!RSAUtils.equalsByPrivateKey(password, account.getPassword(), privateKey)){
            throw new AuthenticationException("账户或密码不正确");
        }

        //查询资源
        //api权限
        //菜单
        //组织架构
        //设备

        //创建Ticket
        return serviceTicketDao.create(account.getId());
    }
}

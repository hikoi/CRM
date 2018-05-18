package com.crm.core.account.service;

import com.crm.commons.security.exception.DuplicateException;
import com.crm.core.account.dao.AccountDao;
import com.crm.core.pem.dao.PemDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.wah.doraemon.entity.Account;
import org.wah.doraemon.security.exception.AuthenticationException;
import org.wah.doraemon.utils.RSAUtils;

@Service
@Transactional(readOnly = true)
public class AccountServiceImpl implements AccountService{

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private PemDao pemDao;

    @Override
    @Transactional(readOnly = false)
    public void register(Account account){
        Assert.notNull(account, "账户信息不能为空");
        Assert.hasText(account.getUsername(), "账户名不能为空");
        Assert.hasText(account.getPassword(), "账户密码不能为空");

        if(accountDao.existByUsername(account.getUsername())){
            throw new DuplicateException("账户[{0}]已注册", account.getUsername());
        }

        accountDao.saveOrUpdate(account);
    }

    @Override
    public Account login(String username, String password){
        Assert.hasText(username, "账户名不能为空");
        Assert.hasText(password, "账户密码不能为空");

        Account account = accountDao.getByUsername(username);

        if(account == null){
            throw new AuthenticationException("账户名或密码不正确");
        }

        //查询私钥
        String privateKey = pemDao.getPrivateKey();

        //校验密码
        if(!RSAUtils.equalsByPrivateKey(password, account.getPassword(), privateKey)){
            throw new AuthenticationException("账户名或密码不正确");
        }

        return account;
    }
}

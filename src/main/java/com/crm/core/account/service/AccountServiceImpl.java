package com.crm.core.account.service;

import com.crm.commons.consts.CacheName;
import com.crm.commons.security.exception.DuplicateException;
import com.crm.core.account.dao.AccountDao;
import com.crm.core.account.dao.PermissionDao;
import com.crm.core.account.dao.RoleDao;
import com.crm.core.account.entity.Permission;
import com.crm.core.account.entity.Role;
import com.crm.core.pem.dao.PemDao;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.wah.doraemon.entity.Account;
import org.wah.doraemon.security.exception.AuthenticationException;
import org.wah.doraemon.utils.HexUtils;
import org.wah.doraemon.utils.ObjectUtils;
import org.wah.doraemon.utils.RSAUtils;
import org.wah.doraemon.utils.RedisUtils;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional(readOnly = true)
public class AccountServiceImpl implements AccountService{

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private PemDao pemDao;

    @Autowired
    private PermissionDao permissionDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private ShardedJedisPool shardedJedisPool;

    @Override
    @Transactional(readOnly = false)
    public void register(String username, String password){
        Assert.hasText(username, "账户名不能为空");
        Assert.hasText(password, "账户密码不能为空");

        if(accountDao.existByUsername(username)){
            throw new DuplicateException("账户[{0}]已注册", username);
        }

        Account account = new Account();
        account.setUsername(username);
        account.setPassword(password);
        //保存
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

        //base64解密
        Base64 base64 = new Base64();
        password = new String(RSAUtils.decryptByPrivateKey(base64.decode(password), privateKey));

        if(!password.equals(RSAUtils.decryptByPrivateKey(account.getPassword(), privateKey))){
            throw new AuthenticationException("账户名或密码不正确");
        }

//        //校验密码
//        if(!RSAUtils.equalsByPrivateKey(password, account.getPassword(), privateKey)){
//            throw new AuthenticationException("账户名或密码不正确");
//        }

        //缓存权限
        Set<Permission> permissions = new HashSet<Permission>();
        //根据账户ID查询
        permissions.addAll(permissionDao.findByAccountId(account.getId()));
        //根据角色ID查询
        List<Role> roles = roleDao.findByAccountId(account.getId());
        if(roles != null && !roles.isEmpty()){
            permissions.addAll(permissionDao.findByRoleIds(ObjectUtils.ids(roles)));
        }

        ShardedJedis jedis = shardedJedisPool.getResource();
        RedisUtils.save(jedis, CacheName.USER_PERMISSIONS + account.getId(), permissions);
        RedisUtils.close(jedis);

        return account;
    }
}

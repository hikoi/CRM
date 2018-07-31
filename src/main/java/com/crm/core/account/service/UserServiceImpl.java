package com.crm.core.account.service;

import com.crm.commons.consts.CacheName;
import com.crm.core.account.dao.UserDao;
import com.crm.core.authentication.entity.ServiceTicket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.wah.doraemon.entity.User;
import org.wah.doraemon.utils.RedisUtils;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

    @Autowired
    private ShardedJedisPool pool;

    @Override
    public User getByTicket(String ticket){
        Assert.hasText(ticket, "票据不能为空");

        try(ShardedJedis jedis = pool.getResource()){
            ServiceTicket st = RedisUtils.get(jedis, CacheName.SERVICE_TICKET + ticket, ServiceTicket.class);

            return userDao.getWithAccountByAccountId(st.getAccountId());
        }
    }
}

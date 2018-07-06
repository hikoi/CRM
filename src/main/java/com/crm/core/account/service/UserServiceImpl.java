package com.crm.core.account.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.ShardedJedisPool;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private ShardedJedisPool pool;
}

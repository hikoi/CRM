package com.crm.core.account.service;

import com.crm.core.permission.dao.FunctionDao;
import com.crm.core.permission.dao.MenuDao;
import com.crm.core.permission.dao.PermissionDao;
import com.crm.core.permission.dao.RoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.ShardedJedisPool;

@Service
public class AccountServiceImpl implements AccountService{

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PermissionDao permissionDao;

    @Autowired
    private FunctionDao functionDao;

    @Autowired
    private MenuDao menuDao;

    @Autowired
    private ShardedJedisPool shardedJedisPool;
}

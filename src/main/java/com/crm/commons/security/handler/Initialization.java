package com.crm.commons.security.handler;

import com.crm.commons.consts.CacheName;
import com.crm.core.permission.dao.FunctionDao;
import com.crm.core.permission.entity.Function;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.wah.doraemon.utils.RedisUtils;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class Initialization{

    @Autowired
    private FunctionDao functionDao;

    @Autowired
    private ShardedJedisPool pool;

    @PostConstruct
    public void needNotAllotFunction(){
        try(ShardedJedis jedis = pool.getResource()){
            List<Function> functions = functionDao.findByNeedAllot(false);

            if(functions != null && !functions.isEmpty()){
                RedisUtils.save(jedis, CacheName.SERVICE_FUNCTION, functions);
            }
        }
    }
}

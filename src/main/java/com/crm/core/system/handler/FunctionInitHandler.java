package com.crm.core.system.handler;

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
public class FunctionInitHandler{

    @Autowired
    private ShardedJedisPool pool;

    @Autowired
    private FunctionDao functionDao;

    @PostConstruct
    public void init(){
        List<Function> functions = functionDao.find(null, null, false, null);

        ShardedJedis jedis = pool.getResource();
        RedisUtils.sadd(jedis, CacheName.NEED_NOT_ALLOT_URL, functions);
        RedisUtils.close(jedis);
    }
}

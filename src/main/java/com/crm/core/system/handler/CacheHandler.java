package com.crm.core.system.handler;

import com.crm.commons.consts.CacheName;
import com.crm.core.account.entity.Permission;
import com.crm.core.account.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.wah.doraemon.utils.RedisUtils;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class CacheHandler{

    @Autowired
    private ShardedJedisPool shardedJedisPool;

    @Autowired
    private PermissionService permissionService;

    @PostConstruct
    public void needNotAllotPermissions(){
        //更新权限资源
        permissionService.synchronize();

        //缓存
        ShardedJedis jedis = shardedJedisPool.getResource();

        List<Permission> needNotAllots = permissionService.find(null, null, false);
        RedisUtils.save(jedis, CacheName.NEED_NOT_ALLOT_URL, needNotAllots);
        RedisUtils.close(jedis);
    }
}

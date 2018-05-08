package com.crm.core.wechat.webservice;

import com.crm.commons.consts.CacheName;
import com.crm.core.wechat.entity.WechatMessage;
import com.crm.core.wechat.service.WechatMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.wah.doraemon.security.response.Responsed;
import org.wah.doraemon.utils.RedisUtils;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import java.util.List;

@RestController
@RequestMapping(value = "/api/1.0/wechatMessage")
public class WechatMessageRestController{

    @Autowired
    private WechatMessageService wechatMessageService;

    @Autowired
    private ShardedJedisPool shardedJedisPool;

    @RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Responsed save(@RequestBody WechatMessage message){
        ShardedJedis jedis = shardedJedisPool.getResource();
        RedisUtils.rpush(jedis, CacheName.WECHAT_MESSAGE, message);
        RedisUtils.close(jedis);

        return new Responsed("保存成功");
    }

    @RequestMapping(value = "/synchronize", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Responsed saveList(@RequestBody List<WechatMessage> messages){
        wechatMessageService.saveList(messages);

        return new Responsed("保存成功");
    }
}

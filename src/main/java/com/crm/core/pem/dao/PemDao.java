package com.crm.core.pem.dao;

import com.crm.commons.consts.CacheName;
import com.crm.commons.consts.Constants;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Repository;
import org.wah.doraemon.security.exception.DataAccessException;
import org.wah.doraemon.utils.RedisUtils;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import java.io.FileInputStream;

@Repository
public class PemDao{

    private Logger logger = LoggerFactory.getLogger(PemDao.class);

    @Autowired
    private ShardedJedisPool shardedJedisPool;

    /**
     * 查询私钥
     */
    public String getPrivateKey(){
        try{
            //读取缓存
            ShardedJedis jedis = shardedJedisPool.getResource();
            String privateKey = RedisUtils.hget(jedis, CacheName.KEY, CacheName.PRIVATE_KEY, String.class);

            if(!StringUtils.isBlank(privateKey)){
                RedisUtils.close(jedis);
                return privateKey;
            }

            PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            Resource resource = resolver.getResource(Constants.PRIVATE_KEY);

            //读取文件
            FileInputStream stream = new FileInputStream(resource.getFile());
            //缓冲
            byte[] buffer = new byte[4096];
            //结果集
            StringBuffer sb = new StringBuffer();

            while(stream.read(buffer) != -1){
                sb.append(new String(buffer));
            }
            privateKey = sb.toString();

            //关闭流
            stream.close();

            //添加缓存
            RedisUtils.hset(jedis, CacheName.KEY, CacheName.PRIVATE_KEY, privateKey);
            RedisUtils.close(jedis);

            return privateKey;
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    /**
     * 查询公钥
     */
    public String getPublicKey(){
        try{
            //读取缓存
            ShardedJedis jedis = shardedJedisPool.getResource();
            String publicKey = RedisUtils.hget(jedis, CacheName.KEY, CacheName.PUBLIC_KEY, String.class);

            if(!StringUtils.isBlank(publicKey)){
                RedisUtils.close(jedis);
                return publicKey;
            }

            PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            Resource resource = resolver.getResource(Constants.PUBLIC_KEY);

            //读取文件
            FileInputStream stream = new FileInputStream(resource.getFile());
            //缓冲
            byte[] buffer = new byte[4096];
            //结果集
            StringBuffer sb = new StringBuffer();

            while(stream.read(buffer) != -1){
                sb.append(new String(buffer));
            }
            publicKey = sb.toString();

            //关闭流
            stream.close();

            //添加缓存
            RedisUtils.hset(jedis, CacheName.KEY, CacheName.PUBLIC_KEY, publicKey);
            RedisUtils.close(jedis);

            return publicKey;
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }
}

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
    private ShardedJedisPool pool;

    /**
     * 查询私钥
     */
    public String getPrivateKey(){
        try(ShardedJedis jedis = pool.getResource()){
            //读取缓存
            String privateKey = RedisUtils.hget(jedis, CacheName.KEY, CacheName.PRIVATE_KEY, String.class);

            if(!StringUtils.isBlank(privateKey)){
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
            //读取长度
            int length = 0;

            while((length = stream.read(buffer)) != -1){
                sb.append(new String(buffer, 0, length));
            }
            privateKey = sb.toString();

            //关闭流
            stream.close();

            //添加缓存
            RedisUtils.hset(jedis, CacheName.KEY, CacheName.PRIVATE_KEY, privateKey);

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
        try(ShardedJedis jedis = pool.getResource()){
            //读取缓存
            String publicKey = RedisUtils.hget(jedis, CacheName.KEY, CacheName.PUBLIC_KEY, String.class);

            if(!StringUtils.isBlank(publicKey)){
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
            //读取长度
            int length = 0;

            while((length = stream.read(buffer)) != -1){
                sb.append(new String(buffer, 0, length));
            }
            publicKey = sb.toString();

            //关闭流
            stream.close();

            //添加缓存
            RedisUtils.hset(jedis, CacheName.KEY, CacheName.PUBLIC_KEY, publicKey);

            return publicKey;
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public String getIMPrivateKey(){
        try(ShardedJedis jedis = pool.getResource()){
            //读取缓存
            String privateKey = RedisUtils.hget(jedis, CacheName.KEY, CacheName.IM_PRIVATE_KEY, String.class);

            if(!StringUtils.isBlank(privateKey)){
                return privateKey;
            }

            PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            Resource resource = resolver.getResource(Constants.IM_PRIVATE_KEY);

            //读取文件
            FileInputStream stream = new FileInputStream(resource.getFile());
            //缓冲
            byte[] buffer = new byte[4096];
            //结果集
            StringBuffer sb = new StringBuffer();
            //读取长度
            int length = 0;

            while((length = stream.read(buffer)) != -1){
                sb.append(new String(buffer, 0, length));
            }
            privateKey = sb.toString();

            //关闭流
            stream.close();

            //添加缓存
            RedisUtils.hset(jedis, CacheName.KEY, CacheName.IM_PRIVATE_KEY, privateKey);

            return privateKey;
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public String getIMPublicKey(){
        try(ShardedJedis jedis = pool.getResource()){
            //读取缓存
            String publicKey = RedisUtils.hget(jedis, CacheName.KEY, CacheName.IM_PUBLIC_KEY, String.class);

            if(!StringUtils.isBlank(publicKey)){
                return publicKey;
            }

            PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            Resource resource = resolver.getResource(Constants.IM_PUBLIC_KEY);

            //读取文件
            FileInputStream stream = new FileInputStream(resource.getFile());
            //缓冲
            byte[] buffer = new byte[4096];
            //结果集
            StringBuffer sb = new StringBuffer();
            //读取长度
            int length = 0;

            while((length = stream.read(buffer)) != -1){
                sb.append(new String(buffer, 0, length));
            }
            publicKey = sb.toString();

            //关闭流
            stream.close();

            //添加缓存
            RedisUtils.hset(jedis, CacheName.KEY, CacheName.IM_PUBLIC_KEY, publicKey);

            return publicKey;
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }
}

package com.crm.core.authentication.dao;

import com.crm.commons.consts.CacheName;
import com.crm.core.authentication.entity.ServiceTicket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import org.wah.doraemon.security.exception.DataAccessException;
import org.wah.doraemon.utils.RedisUtils;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import java.math.BigInteger;
import java.util.Date;

@Repository
public class ServiceTicketDao{

    private Logger logger = LoggerFactory.getLogger(ServiceTicketDao.class);

    @Autowired
    private ShardedJedisPool pool;

    public ServiceTicket create(String accountId){
        try(ShardedJedis jedis = pool.getResource()){
            Assert.hasText(accountId, "账户ID不能为空");

            ServiceTicket ticket = new ServiceTicket();
            ticket.setAccountId(accountId);
            ticket.setExpire(CacheName.TICKET_EXPIRE);
            ticket.setCreateTime(new Date());

            //(accountId + createTime - expire)
            BigInteger master  = new BigInteger(accountId, 16);
            BigInteger assist = new BigInteger(String.valueOf(ticket.getCreateTime().getTime() - ticket.getExpire()), 10);
            ticket.setId(master.add(assist).toString(36));

            //缓存
            RedisUtils.save(jedis, CacheName.SERVICE_TICKET + ticket.getId(), ticket, ticket.getExpire());
            RedisUtils.close(jedis);

            return ticket;
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public boolean authentication(String id){
        try(ShardedJedis jedis = pool.getResource()){
            Assert.hasText(id, "票据ID不能为空");

            //查询缓存
            ServiceTicket ticket = RedisUtils.get(jedis, CacheName.SERVICE_TICKET + id, ServiceTicket.class);

            if(ticket == null){
                return false;
            }

            RedisUtils.expire(jedis, CacheName.SERVICE_TICKET + id, ticket.getExpire());

            return true;
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public void delete(String id){
        try(ShardedJedis jedis = pool.getResource()){
            Assert.hasText(id, "票据ID不能为空");

            //删除缓存
            RedisUtils.delete(jedis, CacheName.SERVICE_TICKET + id);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }
}

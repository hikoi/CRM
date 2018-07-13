package com.crm.core.im.service;

import com.crm.commons.consts.CacheName;
import com.crm.commons.consts.IMConfig;
import com.crm.core.account.dao.AccountDao;
import com.crm.core.account.dao.UserDao;
import com.crm.core.authentication.entity.ServiceTicket;
import com.crm.core.im.consts.IMUserType;
import com.crm.core.im.dao.IMUserDao;
import com.crm.core.im.entity.IMUser;
import com.crm.core.im.utils.IMUtils;
import com.crm.core.im.utils.SignatureUtils;
import com.crm.core.pem.dao.PemDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.wah.doraemon.entity.Account;
import org.wah.doraemon.entity.User;
import org.wah.doraemon.security.exception.AuthenticationException;
import org.wah.doraemon.utils.RSAUtils;
import org.wah.doraemon.utils.RedisUtils;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

@Service
@Transactional(readOnly = true)
public class IMUserServiceImpl implements IMUserService{

    @Autowired
    private IMUserDao imUserDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private PemDao pemDao;

    @Autowired
    private ShardedJedisPool pool;

    @Override
    @Transactional
    public IMUser getByTicket(String ticket){
        try(ShardedJedis jedis = pool.getResource()){
            Assert.hasText(ticket, "票据不能为空");

            //查询ticket
            ServiceTicket st = RedisUtils.get(jedis, CacheName.SERVICE_TICKET + ticket, ServiceTicket.class);
            User user = userDao.getWithAccountByAccountId(st.getAccountId());

            IMUser imUser = imUserDao.getByRelationIdAndType(user.getAccountId(), IMUserType.SELLER);

            if(imUser == null){
                //私钥
                String imPrivateKey = pemDao.getIMPrivateKey();
                //获取签名
                String sig = SignatureUtils.get(IMConfig.SDK_APPID, user.getAccountId(), imPrivateKey);

                //创建IM用户
                imUser = new IMUser();
                imUser.setRelationId(user.getAccountId());
                imUser.setSig(sig);
                imUser.setName(user.getName());
                imUser.setNickname(user.getNickname());
                imUser.setHeadImgUrl(user.getHeadImgUrl());
                imUser.setType(IMUserType.SELLER);
                imUserDao.saveOrUpdate(imUser);

                //注册IM
                IMUtils.accountImport(IMConfig.SDK_APPID, IMConfig.ADMINISTRATOR, IMConfig.ADMINISTRATOR_SIG, imUser);
            }

            return imUser;
        }
    }
}

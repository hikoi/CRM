package com.crm.core.jpush.dao;

import com.crm.core.jpush.dao.mapper.JPushMapper;
import com.crm.core.jpush.entity.JPush;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import org.wah.doraemon.security.exception.DataAccessException;
import org.wah.doraemon.utils.IDGenerator;
import org.wah.doraemon.utils.mybatis.Criteria;
import org.wah.doraemon.utils.mybatis.Restrictions;

import java.util.Date;

@Repository
public class JPushDao{

    private Logger logger = LoggerFactory.getLogger(JPushDao.class);

    @Autowired
    private JPushMapper mapper;

    public void saveOrUpdate(JPush jPush){
        try{
            Assert.notNull(jPush, "极光推送账号信息不能为空");

            if(StringUtils.isBlank(jPush.getId())){
                Assert.hasText(jPush.getRegistrationId(), "极光推送账号ID不能为空");
                Assert.hasText(jPush.getWxno(), "极光推送账号微信号不能为空");

                jPush.setId(IDGenerator.uuid32());
                jPush.setCreateTime(new Date());
                mapper.save(jPush);
            }else{
                jPush.setUpdateTime(new Date());
                mapper.update(jPush);
            }
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public JPush getByWxno(String wxno){
        try{
            Assert.hasText(wxno, "极光推送账号微信号不能为空");

            Criteria criteria = new Criteria();
            criteria.and(Restrictions.eq("wxno", wxno));

            return mapper.get(criteria);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public JPush getByRegistrationId(String registrationId){
        try{
            Assert.hasText(registrationId, "极光推送账号ID不能为空");

            Criteria criteria = new Criteria();
            criteria.and(Restrictions.eq("registrationId", registrationId));

            return mapper.get(criteria);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }
}

package com.crm.core.im.dao;

import com.crm.core.im.dao.mapper.IMCustomMessageMapper;
import com.crm.core.im.entity.IMCustomMessage;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import org.wah.doraemon.security.exception.DataAccessException;
import org.wah.doraemon.utils.IDGenerator;

import java.util.Date;

@Repository
public class IMCustomMessageDao{

    private Logger logger = LoggerFactory.getLogger(IMCustomMessageDao.class);

    @Autowired
    private IMCustomMessageMapper mapper;

    public void saveOrUpdate(IMCustomMessage message){
        try{
            Assert.notNull(message, "IM自定义消息不能为空");

            if(StringUtils.isBlank(message.getId())){
                Assert.notNull(message.getType(), "IM自定义消息类型不能为空");
                Assert.hasText(message.getFromAccount(), "IM自定义消息发送者不能为空");
                Assert.hasText(message.getToAccount(), "IM自定义消息接收者不能为空");
                Assert.hasText(message.getWxno(), "IM自定义消息客户微信号不能为空");
                Assert.notNull(message.getConversationTime(), "IM自定义消息发送时间不能为空");

                message.setId(IDGenerator.uuid32());
                message.setCreateTime(new Date());
                mapper.save(message);
            }else{
                message.setUpdateTime(new Date());
                mapper.update(message);
            }
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }
}

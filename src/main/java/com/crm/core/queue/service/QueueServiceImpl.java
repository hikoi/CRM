package com.crm.core.queue.service;

import com.crm.commons.security.exception.QueueServiceException;
import com.crm.core.wechat.dao.WechatMessageDao;
import com.crm.core.wechat.entity.WechatMessage;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.wah.doraemon.utils.GsonUtils;

import java.util.List;

@Service
@Transactional(readOnly = false)
public class QueueServiceImpl implements QueueService{

    @Autowired
    private WechatMessageDao wechatMessageDao;

    @Override
    @Transactional
    public void saveWechatMessage(String messageString){
        try{
            Assert.hasText(messageString, "微信消息内容不能为空");

            WechatMessage message = GsonUtils.deserialize(messageString, WechatMessage.class);

            Assert.hasText(message.getWxid(), "微信信息wxid不能为空");
            Assert.hasText(message.getWxno(), "微信号不能为空");
            Assert.notNull(message.getConversationTime(), "微信发送时间不能为空");

            wechatMessageDao.saveOrUpdate(message);
        }catch(Exception e){
            throw new QueueServiceException(e.getMessage(), e);
        }
    }


    @Override
    @Transactional
    public void saveWechatMessages(String messagesString){
        try{
            Assert.hasText(messagesString, "微信消息内容不能为空");

            List<WechatMessage> messages = GsonUtils.deserialize(messagesString, new TypeToken<List<WechatMessage>>(){}.getType());

            if(messages != null && !messages.isEmpty()){
                wechatMessageDao.saveList(messages);
            }
        }catch(Exception e){
            throw new QueueServiceException(e.getMessage(), e);
        }
    }
}

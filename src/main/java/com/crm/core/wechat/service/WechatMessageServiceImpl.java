package com.crm.core.wechat.service;

import com.crm.core.wechat.consts.WechatMessageStatus;
import com.crm.core.wechat.consts.WechatMessageType;
import com.crm.core.wechat.dao.WechatMessageDao;
import com.crm.core.wechat.entity.WechatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.wah.doraemon.security.request.Page;
import org.wah.doraemon.security.request.PageRequest;
import org.wah.doraemon.utils.GsonUtils;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class WechatMessageServiceImpl implements WechatMessageService{

    @Autowired
    private WechatMessageDao wechatMessageDao;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    @Transactional(readOnly = false)
    public void save(WechatMessage message){
        Assert.notNull(message, "微信信息不能为空");
        Assert.hasText(message.getWechatId(), "所属微信ID不能为空");
        Assert.notNull(message.getConversationTime(), "微信发送时间不能为空");

        //消息队列处理
        redisTemplate.convertAndSend("save_wechat_message_queue", GsonUtils.serialize(message));
    }

    @Override
    @Transactional(readOnly = false)
    public void synchronize(List<WechatMessage> messages){
        Assert.notEmpty(messages, "微信信息列表不能为空");

        //消息队列处理
        redisTemplate.convertAndSend("save_wechat_messages_queue", GsonUtils.serialize(messages));
    }

    @Override
    public Page<WechatMessage> page(PageRequest pageRequest, String accountId, String wechatId,
                                    String wxid, WechatMessageType type, WechatMessageStatus status){
        Assert.notNull(pageRequest, "分页信息不能为空");

        return wechatMessageDao.page(pageRequest, accountId, wechatId, wxid, type, status);
    }
}

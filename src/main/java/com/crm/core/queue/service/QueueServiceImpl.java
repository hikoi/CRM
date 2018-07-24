package com.crm.core.queue.service;

import com.crm.commons.consts.IMConfig;
import com.crm.commons.security.exception.QueueServiceException;
import com.crm.commons.utils.CharUtils;
import com.crm.core.im.entity.IMCustomMessage;
import com.crm.core.im.entity.IMMessage;
import com.crm.core.im.utils.IMMessageUtils;
import com.crm.core.im.utils.IMUtils;
import com.crm.core.wechat.consts.PurposeType;
import com.crm.core.wechat.consts.WechatMessageStatus;
import com.crm.core.wechat.dao.WechatDao;
import com.crm.core.wechat.dao.WechatFriendDao;
import com.crm.core.wechat.dao.WechatMessageDao;
import com.crm.core.wechat.entity.Wechat;
import com.crm.core.wechat.entity.WechatFriend;
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

    @Autowired
    private WechatDao wechatDao;

    @Autowired
    private WechatFriendDao wechatFriendDao;

    @Override
    @Transactional
    public void saveWechatMessage(String messageString){
        try{
            Assert.hasText(messageString, "微信消息内容不能为空");

            WechatMessage message = GsonUtils.deserialize(messageString, WechatMessage.class);

            Assert.hasText(message.getWxid(), "微信信息wxid不能为空");
            Assert.hasText(message.getWechatId(), "所属微信ID不能为空");
            Assert.notNull(message.getConversationTime(), "微信发送时间不能为空");

            //查询微信
            Wechat wechat = wechatDao.getById(message.getWechatId());
            //查询微信好友
            WechatFriend friend = wechatFriendDao.getByWechatIdAndWxid(message.getWechatId(), message.getWxid());
            //保存客户ID
            message.setSellerId(friend.getSellerId());
//            message.setContent(CharUtils.toUnicode(message.getContent()));
            //保存微信消息
            wechatMessageDao.saveOrUpdate(message);

            //发送到IM
            if(wechat.getType().equals(PurposeType.DISTRIBUTE) && !message.equals(WechatMessageStatus.SEND_SUCCESS)){
                //创建自定义消息
                IMCustomMessage custom = new IMCustomMessage();
                custom.setId(friend.getId());
                custom.setConversationTime(message.getConversationTime());
                custom.setContent(message.getExtract());
                custom.setWxno(friend.getWxno());
                custom.setFromAccount(message.getWechatId());
                custom.setToAccount(friend.getSellerId());
                custom.setType(message.getType());

                switch(message.getType()){
                    case TEXT://文本
                    case IMAGE://图片
                    case VOICE://语音
                    case EMOTICONS://动画图片
                    case LUCKY_PACKAGE://红包
                    case TRANSFER://转账
                    case UNKNOWN://未知
                        IMMessage imMessage = IMMessageUtils.createTextMsg(custom.getFromAccount(), custom.getToAccount(), GsonUtils.serialize(custom));
                        IMUtils.sendMsg(IMConfig.SDK_APPID, IMConfig.ADMINISTRATOR, IMConfig.ADMINISTRATOR_SIG, imMessage);
                        break;
                    case SYSTEM://系统消息
                    case PERSON_CARD://个人名片
                    case SHARE://分享
                    case ONLINE_TALK://视频/语音聊天
                    default:
                        break;
                }
            }
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

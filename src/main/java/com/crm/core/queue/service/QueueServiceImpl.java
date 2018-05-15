package com.crm.core.queue.service;

import com.crm.commons.consts.JPushConfig;
import com.crm.commons.security.exception.QueueServiceException;
import com.crm.commons.utils.CharUtils;
import com.crm.core.jpush.dao.JPushDao;
import com.crm.core.jpush.entity.JPush;
import com.crm.core.sensitive.dao.SensitiveWordDao;
import com.crm.core.sensitive.entity.SensitiveWord;
import com.crm.core.wechat.dao.WechatMessageDao;
import com.crm.core.wechat.entity.WechatMessage;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.wah.doraemon.entity.consts.UsingState;
import org.wah.doraemon.utils.GsonUtils;
import org.wah.doraemon.utils.HttpClientUtils;

import java.text.MessageFormat;
import java.util.*;

@Service
@Transactional(readOnly = true)
public class QueueServiceImpl implements QueueService{

    @Autowired
    private SensitiveWordDao sensitiveWordDao;

    @Autowired
    private JPushDao jPushDao;

    @Autowired
    private WechatMessageDao wechatMessageDao;

    /**
     * 敏感词更新推送
     */
    @Override
    public void pushSensitive(String wxno){
        try(CloseableHttpClient client = HttpClientUtils.createHttpsClient()){
            Assert.hasText(wxno, "极光推送账号微信号不能为空");

            //根据微信号查询设备所有敏感词
            List<SensitiveWord> sensitives = sensitiveWordDao.findByWxno(wxno, UsingState.USABLE);
            //去重
            Set<SensitiveWord> set = new HashSet<SensitiveWord>(sensitives);

            StringBuffer sb = new StringBuffer();
            Iterator<SensitiveWord> iterator = set.iterator();
            while(iterator.hasNext()){
                sb.append(CharUtils.toUnicode(iterator.next().getContent()));

                if(iterator.hasNext()){
                    sb.append('|');
                }
            }

            //正则
            String regEx = MessageFormat.format("[\\s\\S]*({0})+[\\s\\S]*", sb.toString());
            //极光账号
            JPush jPush = jPushDao.getByWxno(wxno);
            if(jPush != null && StringUtils.isNotBlank(jPush.getRegistrationId())) {
                //参数
                Map<String, Object> params = new HashMap<String, Object>();

                params.put("platform", Arrays.asList(JPushConfig.PLATFORM_ANDROID));

                Map<String, Object> audience = new HashMap<String, Object>();
                audience.put("registration_id", Arrays.asList(jPush.getRegistrationId()));
                params.put("audience", audience);

                Map<String, Object> message = new HashMap<String, Object>();
                message.put("msg_content", regEx);
                message.put("title", "敏感词更新");
                message.put("content_type", "text");
                params.put("message", message);

                HttpPost post = HttpClientUtils.post(JPushConfig.PUSH_API, null, (Object) params);
                post.addHeader("Authorization", JPushConfig.AUTH_STRING());
                post.addHeader("Content-Type", "application/json");

                HttpResponse response = client.execute(post);

                if(!HttpClientUtils.isOk(response)){
                    throw new QueueServiceException(EntityUtils.toString(response.getEntity()));
                }
            }
        }catch(Exception e){
            throw new QueueServiceException(e.getMessage(), e);
        }
    }

    /**
     * 微信消息实时同步
     */
    @Override
    @Transactional(readOnly = false)
    public void saveWechatMessage(String messageString){
        try{
            Assert.hasText(messageString, "微信消息内容不能为空");

            WechatMessage message = GsonUtils.deserialize(messageString, WechatMessage.class);

            Assert.hasText(message.getWxid(), "微信信息wxid不能为空");
            Assert.hasText(message.getWechatId(), "微信ID不能为空");

            wechatMessageDao.saveOrUpdate(message);
        }catch(Exception e){
            throw new QueueServiceException(e.getMessage(), e);
        }
    }

    /**
     * 微信消息定时同步
     */
    @Override
    @Transactional(readOnly = false)
    public void synchronizeWechatMessage(String messagesString){
        try{
            Assert.hasText(messagesString, "微信消息内容不能为空");

            List<WechatMessage> messages = GsonUtils.deserialize(messagesString, new TypeToken<List<WechatMessage>>(){}.getType());

            Assert.notEmpty(messages, "微信消息列表不能为空");

            wechatMessageDao.saveList(messages);
        }catch(Exception e){
            throw new QueueServiceException(e.getMessage(), e);
        }
    }
}

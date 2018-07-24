package com.crm.core.wechat.dao;

import com.crm.core.wechat.consts.WechatMessageStatus;
import com.crm.core.wechat.consts.WechatMessageType;
import com.crm.core.wechat.dao.mapper.WechatMessageMapper;
import com.crm.core.wechat.entity.WechatMessage;
import com.crm.core.wechat.utils.WechatMessageUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import org.wah.doraemon.security.exception.DataAccessException;
import org.wah.doraemon.security.request.Page;
import org.wah.doraemon.security.request.PageRequest;
import org.wah.doraemon.utils.IDGenerator;
import org.wah.doraemon.utils.mybatis.Criteria;
import org.wah.doraemon.utils.mybatis.Restrictions;

import java.util.Date;
import java.util.List;

@Repository
public class WechatMessageDao{

    private Logger logger = LoggerFactory.getLogger(WechatMessageDao.class);

    @Autowired
    private WechatMessageMapper mapper;

    public void saveOrUpdate(WechatMessage message){
        try{
            Assert.notNull(message, "微信信息不能为空");

            if(StringUtils.isBlank(message.getId())){
                Assert.hasText(message.getWxid(), "微信信息wxid不能为空");
                Assert.hasText(message.getWechatId(), "所属微信ID不能为空");
                Assert.notNull(message.getConversationTime(), "微信发送时间不能为空");

                message.setId(IDGenerator.uuid32());
                message.setExtract(WechatMessageUtils.extract(message));
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

    public void saveList(List<WechatMessage> messages){
        try{
            Assert.notEmpty(messages, "微信消息列表不能为空");

            final Date now = new Date();

            for(WechatMessage message : messages){
                Assert.hasText(message.getWxid(), "微信信息wxid不能为空");
                Assert.hasText(message.getWechatId(), "所属微信ID不能为空");
                Assert.notNull(message.getConversationTime(), "微信发送时间不能为空");

                message.setId(IDGenerator.uuid32());
                message.setExtract(WechatMessageUtils.extract(message));
                message.setCreateTime(now);
            }

            mapper.saveList(messages);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public Page<WechatMessage> page(PageRequest pageRequest, String accountId, String wechatId,
                                    String wxid, WechatMessageType type, WechatMessageStatus status){
        try{
            Assert.notNull(pageRequest, "分页信息不能为空");

            Criteria criteria = new Criteria();
            criteria.limit(Restrictions.limit(pageRequest.getOffset(), pageRequest.getPageSize()));
            criteria.sort(Restrictions.asc("conversationTime"));

            if(StringUtils.isNotBlank(accountId)){
                criteria.and(Restrictions.eq("accountId", accountId));
            }
            if(StringUtils.isNotBlank(wechatId)){
                criteria.and(Restrictions.eq("wechatId", wechatId));
            }
            if(StringUtils.isNotBlank(wxid)){
                criteria.and(Restrictions.eq("wxid", wxid));
            }
            if(type != null){
                criteria.and(Restrictions.eq("type", type.getId()));
            }
            if(status != null){
                criteria.and(Restrictions.eq("status", status.getId()));
            }

            List<WechatMessage> list = mapper.find(criteria);
            Long total = mapper.count(criteria);

            return new Page<WechatMessage>(list, total, pageRequest);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }
}

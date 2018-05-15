package com.crm.core.sensitive.service;

import com.crm.commons.utils.CharUtils;
import com.crm.core.sensitive.dao.SensitiveWordDao;
import com.crm.core.sensitive.entity.SensitiveWord;
import com.crm.core.team.dao.GroupsDao;
import com.crm.core.wechat.dao.WechatDao;
import com.crm.core.wechat.entity.Wechat;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.wah.doraemon.entity.consts.UsingState;
import org.wah.doraemon.security.request.Page;
import org.wah.doraemon.security.request.PageRequest;

import java.text.MessageFormat;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Service
@Transactional(readOnly = true)
public class SensitiveWordServiceImpl implements SensitiveWordService{

    @Autowired
    private SensitiveWordDao sensitiveWordDao;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private WechatDao wechatDao;

    @Autowired
    private GroupsDao groupsDao;

    @Override
    @Transactional(readOnly = false)
    public void save(SensitiveWord sensitiveWord){
        Assert.notNull(sensitiveWord, "敏感词信息不能为空");

        sensitiveWordDao.saveOrUpdate(sensitiveWord);
    }

    @Override
    @Transactional(readOnly = false)
    public void update(SensitiveWord sensitiveWord){
        Assert.notNull(sensitiveWord, "敏感词信息不能为空");
        Assert.hasText(sensitiveWord.getId(), "敏感词信息不能为空");

        sensitiveWordDao.saveOrUpdate(sensitiveWord);
    }

    @Override
    public SensitiveWord getById(String id){
        Assert.hasText(id, "敏感词ID不能为空");

        return sensitiveWordDao.getById(id);
    }

    @Override
    public Page<SensitiveWord> page(PageRequest pageRequest, String content, UsingState state, String groupId){
        Assert.notNull(pageRequest, "分页信息不能为空");

        return sensitiveWordDao.page(pageRequest, content, state, groupId);
    }

    @Override
    public String getRegEx(String wxno){
        Assert.hasText(wxno, "微信号不能为空");

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

        return MessageFormat.format("[\\s\\S]*[{0}][\\s\\S]*", sb.toString());
    }

    @Override
    @Transactional(readOnly = false)
    public void updateRelationByGroupId(String groupId, List<String> sensitiveIds){
        Assert.hasText(groupId, "分组ID不能为空");

        //更新
        sensitiveWordDao.updateRelationByGroupId(groupId, sensitiveIds);
        //查询微信号
        List<String> wxnos = groupsDao.findWxnoByGroupId(groupId);
        for(String wxno : wxnos){
            if(StringUtils.isNotBlank(wxno)){
                //消息队列处理
                redisTemplate.convertAndSend("push_sensitive_queue", wxno);
            }
        }
    }

    @Override
    @Transactional(readOnly = false)
    public void updateRelationByWechatId(String wechatId, List<String> sensitiveIds){
        Assert.hasText(wechatId, "微信ID不能为空");

        //更新
        sensitiveWordDao.updateRelationByWechatId(wechatId, sensitiveIds);
        //消息队列处理
        Wechat wechat = wechatDao.getById(wechatId);
        redisTemplate.convertAndSend("push_sensitive_queue", wechat.getWxno());
    }
}

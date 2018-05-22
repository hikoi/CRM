package com.crm.core.group.service;

import com.crm.core.group.dao.GroupsDao;
import com.crm.core.group.entity.Groups;
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

import java.util.List;

@Service
@Transactional(readOnly = true)
public class GroupsServiceImpl implements GroupsService{

    @Autowired
    private GroupsDao groupsDao;

    @Autowired
    private WechatDao wechatDao;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    @Transactional(readOnly = false)
    public void save(Groups group){
        Assert.notNull(group, "分组信息不能为空");

        groupsDao.saveOrUpdate(group);
    }

    @Override
    @Transactional(readOnly = false)
    public void update(Groups group){
        Assert.notNull(group, "分组信息不能为空");
        Assert.hasText(group.getId(), "分组ID不能为空");

        groupsDao.saveOrUpdate(group);
    }

    @Override
    public Groups getById(String id){
        Assert.hasText(id, "分组ID不能为空");

        return groupsDao.getById(id);
    }

    @Override
    public Page<Groups> page(PageRequest pageRequest, String id, String name, UsingState state){
        Assert.notNull(pageRequest, "分组信息不能为空");

        return groupsDao.page(pageRequest, id, name, state);
    }

    @Override
    @Transactional(readOnly = false)
    public void updateRelationByGroupId(String groupId, List<String> wechatIds){
        Assert.hasText(groupId, "分组ID不能为空");

        //更新
        groupsDao.updateRelationByGroupId(groupId, wechatIds);
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
    public void updateRelationByWechatId(String wechatId, List<String> groupIds){
        Assert.hasText(wechatId, "微信ID不能为空");

        //更新
        groupsDao.updateRelationByWechatId(wechatId, groupIds);
        //消息队列处理
        Wechat wechat = wechatDao.getById(wechatId);
        redisTemplate.convertAndSend("push_sensitive_queue", wechat.getWxno());
    }
}

package com.crm.core.group.service;

import com.crm.core.group.dao.GroupsDao;
import com.crm.core.group.entity.Groups;
import com.crm.core.wechat.dao.WechatDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.wah.doraemon.entity.consts.UsingState;
import org.wah.doraemon.security.request.Page;
import org.wah.doraemon.security.request.PageRequest;

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
    @Transactional
    public void save(Groups group){
        Assert.notNull(group, "分组信息不能为空");

        groupsDao.saveOrUpdate(group);
    }

    @Override
    @Transactional
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
    public Page<Groups> page(PageRequest pageRequest, String name, UsingState state){
        Assert.notNull(pageRequest, "分组信息不能为空");

        return groupsDao.page(pageRequest, name, state);
    }
}

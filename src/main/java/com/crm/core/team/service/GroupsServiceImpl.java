package com.crm.core.team.service;

import com.crm.core.team.dao.GroupDao;
import com.crm.core.team.entity.Groups;
import org.springframework.beans.factory.annotation.Autowired;
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
    private GroupDao groupDao;

    @Override
    @Transactional(readOnly = false)
    public void save(Groups group){
        Assert.notNull(group, "分组信息不能为空");

        groupDao.saveOrUpdate(group);
    }

    @Override
    @Transactional(readOnly = false)
    public void update(Groups group){
        Assert.notNull(group, "分组信息不能为空");
        Assert.hasText(group.getId(), "分组ID不能为空");

        groupDao.saveOrUpdate(group);
    }

    @Override
    public Groups getById(String id){
        Assert.hasText(id, "分组ID不能为空");

        return groupDao.getById(id);
    }

    @Override
    public Page<Groups> page(PageRequest pageRequest, String id, String name, UsingState state){
        Assert.notNull(pageRequest, "分组信息不能为空");

        return groupDao.page(pageRequest, id, name, state);
    }

    @Override
    @Transactional(readOnly = false)
    public void updateRelationByGroupId(String groupId, List<String> wechatIds){
        Assert.hasText(groupId, "分组ID不能为空");

        groupDao.updateRelationByGroupId(groupId, wechatIds);
    }

    @Override
    @Transactional(readOnly = false)
    public void updateRelationByWechatId(String wechatId, List<String> groupIds){
        Assert.hasText(wechatId, "微信ID不能为空");

        groupDao.updateRelationByWechatId(wechatId, groupIds);
    }
}

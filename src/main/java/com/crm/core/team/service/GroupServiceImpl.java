package com.crm.core.team.service;

import com.crm.core.team.dao.GroupDao;
import com.crm.core.team.entity.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.wah.doraemon.entity.consts.UsingState;
import org.wah.doraemon.security.request.Page;
import org.wah.doraemon.security.request.PageRequest;

@Service
@Transactional(readOnly = true)
public class GroupServiceImpl implements GroupService{

    @Autowired
    private GroupDao groupDao;

    @Override
    @Transactional(readOnly = false)
    public void save(Group group){
        Assert.notNull(group, "分组信息不能为空");

        groupDao.saveOrUpdate(group);
    }

    @Override
    @Transactional(readOnly = false)
    public void update(Group group){
        Assert.notNull(group, "分组信息不能为空");
        Assert.hasText(group.getId(), "分组ID不能为空");

        groupDao.saveOrUpdate(group);
    }

    @Override
    public Group getById(String id){
        Assert.hasText(id, "分组ID不能为空");

        return groupDao.getById(id);
    }

    @Override
    public Page<Group> page(PageRequest pageRequest, String id, String name, UsingState state){
        Assert.notNull(pageRequest, "分组信息不能为空");

        return groupDao.page(pageRequest, id, name, state);
    }
}

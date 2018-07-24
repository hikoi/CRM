package com.crm.core.group.service;

import com.crm.core.group.dao.GroupsWechatDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class GroupsWechatServiceImpl implements GroupsWechatService{

    @Autowired
    private GroupsWechatDao groupsWechatDao;

    @Override
    @Transactional
    public void save(String groupsId, String wechatId){
        Assert.hasText(groupsId, "分组ID不能为空");
        Assert.hasText(wechatId, "微信ID不能为空");

        //添加新分组信息
        groupsWechatDao.save(groupsId, wechatId);
    }

    @Override
    @Transactional
    public void saveList(String groupsId, List<String> wechatIds){
        Assert.hasText(groupsId, "分组ID不能为空");

        //删除旧分组信息
        groupsWechatDao.deleteByGroupsId(groupsId);
        //添加新分组信息
        if(wechatIds != null && !wechatIds.isEmpty()){
            groupsWechatDao.saveList(groupsId, wechatIds);
        }
    }
}

package com.crm.core.group.service;

import com.crm.core.group.dao.GroupsSellerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class GroupsSellerServiceImpl implements GroupsSellerService{

    @Autowired
    private GroupsSellerDao groupsSellerDao;

    @Override
    @Transactional
    public void save(String groupsId, String sellerId){
        Assert.hasText(groupsId, "新分组ID不能为空");
        Assert.hasText(sellerId, "销售ID不能为空");

        //添加新分组信息
        groupsSellerDao.save(groupsId, sellerId);
    }

    @Override
    @Transactional
    public void saveList(String groupsId, List<String> sellerIds){
        Assert.hasText(groupsId, "分组ID不能为空");

        //删除旧分组信息
        groupsSellerDao.deleteByGroupsId(groupsId);
        //添加新分组信息
        if(sellerIds != null && !sellerIds.isEmpty()){
            groupsSellerDao.saveList(groupsId, sellerIds);
        }
    }
}

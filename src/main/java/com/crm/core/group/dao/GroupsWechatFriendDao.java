package com.crm.core.group.dao;

import com.crm.core.group.consts.GroupType;
import com.crm.core.group.dao.mapper.GroupsWechatFriendMapper;
import com.crm.core.group.entity.GroupsWechatFriend;
import com.crm.core.wechat.consts.WechatFriendType;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import org.wah.doraemon.entity.consts.UsingState;
import org.wah.doraemon.security.exception.DataAccessException;
import org.wah.doraemon.utils.mybatis.Criteria;
import org.wah.doraemon.utils.mybatis.Restrictions;

import java.util.List;

@Repository
public class GroupsWechatFriendDao {

    private Logger logger = LoggerFactory.getLogger(GroupsWechatFriendDao.class);

    @Autowired
    private GroupsWechatFriendMapper mapper;

    public void save(String groupsId, String friendId){
        try{
            Assert.hasText(groupsId, "分组ID不能为空");
            Assert.hasText(friendId, "微信好友ID不能为空");

            mapper.save(groupsId, friendId);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public void saveList(String groupsId, List<String> friendIds){
        try{
            Assert.hasText(groupsId, "分组ID不能为空");
            Assert.notEmpty(friendIds, "微信好友ID列表不能为空");

            mapper.saveList(groupsId, friendIds);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public void deleteByGroupsId(String groupsId){
        try{
            Assert.hasText(groupsId, "分组ID不能为空");

            Criteria criteria = new Criteria();
            criteria.and(Restrictions.eq("groups_id", groupsId));

            mapper.delete(criteria);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public void deleteByFriendId(String friendId){
        try{
            Assert.hasText(friendId, "微信好友ID不能为空");

            Criteria criteria = new Criteria();
            criteria.and(Restrictions.eq("friendId", friendId));

            mapper.delete(criteria);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public void deleteByGroupsIdAndFriendId(String groupsId, String friendId){
        try{
            Assert.hasText(groupsId, "分组ID不能为空");
            Assert.hasText(friendId, "微信好友ID不能为空");

            Criteria criteria = new Criteria();
            criteria.and(Restrictions.eq("groupsId", groupsId));
            criteria.and(Restrictions.eq("friendId", friendId));

            mapper.delete(criteria);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public List<GroupsWechatFriend> find(String companyId, String sellerId, String groupsId, String groupsName,
                                         UsingState groupsState, WechatFriendType friendType){
        try{
            Criteria criteria = new Criteria();
            criteria.and(Restrictions.eq("g.type", GroupType.FRIEND));

            if(StringUtils.isNotBlank(companyId)){
                criteria.and(Restrictions.eq("g.companyId", companyId));
            }
            if(StringUtils.isNotBlank(sellerId)){
                criteria.and(Restrictions.eq("f.sellerId", sellerId));
            }
            if(StringUtils.isNotBlank(groupsId)){
                criteria.and(Restrictions.eq("gf.groupsId", groupsId));
            }
            if(StringUtils.isNotBlank(groupsName)){
                criteria.and(Restrictions.like("g.name", groupsName));
            }
            if(groupsState != null){
                criteria.and(Restrictions.eq("g.state", groupsState.getId()));
            }
            if(friendType != null){
                criteria.and(Restrictions.eq("f.type", friendType.getId()));
            }

            return mapper.find(criteria);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }
}

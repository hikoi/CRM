package com.crm.core.group.dao;

import com.crm.core.group.consts.GroupType;
import com.crm.core.group.dao.mapper.GroupsWechatMapper;
import com.crm.core.group.entity.GroupsWechat;
import com.crm.core.wechat.consts.PurposeType;
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
public class GroupsWechatDao{

    private Logger logger = LoggerFactory.getLogger(GroupsWechatDao.class);

    @Autowired
    private GroupsWechatMapper mapper;

    public void save(String groupsId, String wechatId){
        try{
            Assert.hasText(groupsId, "分组ID不能为空");
            Assert.hasText(wechatId, "微信ID不能为空");

            mapper.save(groupsId, wechatId);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public void saveList(String groupsId, List<String> wechatIds){
        try{
            Assert.hasText(groupsId, "分组ID不能为空");
            Assert.notEmpty(wechatIds, "微信ID列表不能为空");

            mapper.saveList(groupsId, wechatIds);
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

    public void deleteByWechatId(String wechatId){
        try{
            Assert.hasText(wechatId, "微信ID不能为空");

            Criteria criteria = new Criteria();
            criteria.and(Restrictions.eq("wechatId", wechatId));

            mapper.delete(criteria);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public void deleteByGroupsIdAndWechatId(String groupsId, String wechatId){
        try{
            Assert.hasText(groupsId, "分组ID不能为空");
            Assert.hasText(wechatId, "微信ID不能为空");

            Criteria criteria = new Criteria();
            criteria.and(Restrictions.eq("groupsId", groupsId));
            criteria.and(Restrictions.eq("wechatId", wechatId));

            mapper.delete(criteria);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public List<GroupsWechat> find(String companyId, String wechatId, String groupsId, String groupsName,
                                   UsingState groupsState, PurposeType purposeType){
        try{
            Criteria criteria = new Criteria();
            criteria.and(Restrictions.eq("g.type", GroupType.WECHAT.getId()));

            if(StringUtils.isNotBlank(companyId)){
                criteria.and(Restrictions.eq("g.companyId", companyId));
            }
            if(StringUtils.isNotBlank(wechatId)){
                criteria.and(Restrictions.eq("gw.wechatId", wechatId));
            }
            if(StringUtils.isNotBlank(groupsId)){
                criteria.and(Restrictions.eq("gw.groupsId", groupsId));
            }
            if(StringUtils.isNotBlank(groupsName)){
                criteria.and(Restrictions.like("g.name", groupsName));
            }
            if(groupsState != null){
                criteria.and(Restrictions.eq("g.state", groupsState.getId()));
            }
            if(purposeType != null){
                criteria.and(Restrictions.eq("w.type", purposeType.getId()));
            }

            return mapper.find(criteria);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }
}

package com.crm.core.group.dao;

import com.crm.core.group.consts.GroupType;
import com.crm.core.group.dao.mapper.GroupsSellerMapper;
import com.crm.core.group.entity.GroupsSeller;
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
public class GroupsSellerDao{

    private Logger logger = LoggerFactory.getLogger(GroupsSellerDao.class);

    @Autowired
    private GroupsSellerMapper mapper;

    public void save(String groupsId, String sellerId){
        try{
            Assert.hasText(groupsId, "分组ID不能为空");
            Assert.hasText(sellerId, "销售ID不能为空");

            mapper.save(groupsId, sellerId);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public void saveList(String groupsId, List<String> sellerIds){
        try{
            Assert.hasText(groupsId, "分组ID不能为空");
            Assert.notEmpty(sellerIds, "销售ID列表不能为空");

            mapper.saveList(groupsId, sellerIds);
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

    public void deleteBySellerId(String sellerId){
        try{
            Assert.hasText(sellerId, "销售ID不能为空");

            Criteria criteria = new Criteria();
            criteria.and(Restrictions.eq("sellerId", sellerId));

            mapper.delete(criteria);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public void deleteByGroupsIdAndSellerId(String groupsId, String sellerId){
        try{
            Assert.hasText(groupsId, "分组ID不能为空");
            Assert.hasText(sellerId, "销售ID不能为空");

            Criteria criteria = new Criteria();
            criteria.and(Restrictions.eq("groupsId", groupsId));
            criteria.and(Restrictions.eq("sellerId", sellerId));

            mapper.delete(criteria);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public List<GroupsSeller> find(String companyId, String sellerId, List<String> groupsIds, String groupsName,
                                   UsingState groupsState, String nickname, String name, String username){
        try{
            Criteria criteria = new Criteria();
            criteria.and(Restrictions.eq("g.type", GroupType.SELLER.getId()));

            if(StringUtils.isNotBlank(companyId)){
                criteria.and(Restrictions.eq("g.companyId", companyId));
            }
            if(StringUtils.isNotBlank(sellerId)){
                criteria.and(Restrictions.eq("s.accountId", sellerId));
            }
            if(groupsIds != null && !groupsIds.isEmpty()){
                criteria.and(Restrictions.in("gs.groupsId", groupsIds));
            }
            if(StringUtils.isNotBlank(groupsName)){
                criteria.and(Restrictions.like("g.name", groupsName));
            }
            if(groupsState != null){
                criteria.and(Restrictions.eq("g.state", groupsState.getId()));
            }
            if(StringUtils.isNotBlank(nickname)){
                criteria.and(Restrictions.like("s.nickname", nickname));
            }
            if(StringUtils.isNotBlank(nickname)){
                criteria.and(Restrictions.like("s.name", name));
            }
            if(StringUtils.isNotBlank(username)){
                criteria.and(Restrictions.like("a.username", username));
            }

            return mapper.find(criteria);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }
}

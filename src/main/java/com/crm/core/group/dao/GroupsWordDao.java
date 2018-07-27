package com.crm.core.group.dao;

import com.crm.core.group.consts.GroupType;
import com.crm.core.group.dao.mapper.GroupsWordMapper;
import com.crm.core.words.entity.Word;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import org.wah.doraemon.entity.consts.UsingState;
import org.wah.doraemon.security.exception.DataAccessException;
import org.wah.doraemon.security.request.Page;
import org.wah.doraemon.security.request.PageRequest;
import org.wah.doraemon.utils.mybatis.Criteria;
import org.wah.doraemon.utils.mybatis.Restrictions;

import java.util.List;

@Repository
public class GroupsWordDao{

    private Logger logger = LoggerFactory.getLogger(GroupsSellerDao.class);

    @Autowired
    private GroupsWordMapper mapper;

    public void save(String groupsId, String wordId){
        try{
            Assert.hasText(groupsId, "微信分组ID不能为空");
            Assert.hasText(wordId, "敏感词ID不能为空");

            mapper.save(groupsId, wordId);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public void saveList(String groupsId, List<String> wordIds){
        try{
            Assert.hasText(groupsId, "微信ID不能为空");
            Assert.notEmpty(wordIds, "敏感词ID列表不能为空");

            mapper.saveList(groupsId, wordIds);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public void deleteByGroupsId(String groupsId){
        try{
            Assert.hasText(groupsId, "微信ID不能为空");

            Criteria criteria = new Criteria();
            criteria.and(Restrictions.eq("gs.groupsIdd", groupsId));

            mapper.delete(criteria);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public Page<Word> page(PageRequest pageRequest, String groupsId, String wechatId, UsingState groupsState){
        try{
            Assert.notNull(pageRequest, "分页信息不能为空");

            Criteria criteria = new Criteria();
            criteria.limit(Restrictions.limit(pageRequest.getOffset(), pageRequest.getPageSize()));
            criteria.sort(Restrictions.desc("s.content"));

            if(StringUtils.isNotBlank(groupsId)){
                criteria.and(Restrictions.eq("gs.groupsId", groupsId));
            }
            if(StringUtils.isNotBlank(wechatId)){
                criteria.and(Restrictions.eq("gw.wechatId", wechatId));
            }
            if(groupsState != null){
                criteria.and(Restrictions.eq("g.state", groupsState.getId()));
            }
            if(StringUtils.isNotBlank(groupsId) || groupsState != null){
                criteria.and(Restrictions.eq("g.type", GroupType.WECHAT.getId()));
            }

            List<Word> list  = mapper.find(criteria);
            Long       total = mapper.count(criteria);

            return new Page<Word>(list, total, pageRequest);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }
}

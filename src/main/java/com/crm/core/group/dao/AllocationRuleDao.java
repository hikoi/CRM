package com.crm.core.group.dao;

import com.crm.core.group.consts.AllocationType;
import com.crm.core.group.dao.mapper.AllocationRuleMapper;
import com.crm.core.group.entity.AllocationRule;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import org.wah.doraemon.entity.consts.UsingState;
import org.wah.doraemon.security.exception.DataAccessException;
import org.wah.doraemon.utils.IDGenerator;
import org.wah.doraemon.utils.mybatis.Criteria;
import org.wah.doraemon.utils.mybatis.Restrictions;

import java.util.Date;
import java.util.List;

@Repository
public class AllocationRuleDao{

    private Logger logger = LoggerFactory.getLogger(AllocationRuleDao.class);

    @Autowired
    private AllocationRuleMapper mapper;

    public void saveOrUpdate(AllocationRule rule){
        try{
            Assert.notNull(rule, "分配规则信息不能为空");

            if(StringUtils.isBlank(rule.getId())){
                Assert.notNull(rule.getState(), "分配规则状态不能为空");
                Assert.notNull(rule.getType(), "分配规则类型不能为空");
                Assert.notNull(rule.getOnlineOnly(), "分配规则分配销售状态不能为空");

                rule.setId(IDGenerator.uuid32());
                rule.setCreateTime(new Date());
                mapper.save(rule);
            }else{
                rule.setUpdateTime(new Date());
                mapper.update(rule);
            }
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public void deleteById(String id){
        try{
            Assert.hasText(id, "分配规则ID不能为空");

            Criteria criteria = new Criteria();
            criteria.and(Restrictions.eq("a.id", id));

            mapper.delete(criteria);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public void saveRelations(AllocationRule rule){
        try{
            Assert.notNull(rule, "分配规则信息不能为空");
            Assert.notEmpty(rule.getWechatGroups(), "分配的微信分组ID不能为空");
            Assert.notEmpty(rule.getRegions(), "分配的范围ID不能为空");

            mapper.saveRelations(rule);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public void deleteRelationsById(String id){
        try{
            Assert.hasText(id, "分配规则ID不能为空");

            Criteria criteria = new Criteria();
            criteria.and(Restrictions.eq("ruleId", id));

            mapper.deleteRelations(criteria);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public List<AllocationRule> find(UsingState state, AllocationType type, List<String> wechatGroupsIds){
        try{
            Criteria criteria = new Criteria();

            if(state != null){
                criteria.and(Restrictions.eq("a.state", state));
            }
            if(type != null){
                criteria.and(Restrictions.eq("a.type", type));
            }
            if(wechatGroupsIds != null && !wechatGroupsIds.isEmpty()){
                criteria.and(Restrictions.in("ag.groupsWechatId", wechatGroupsIds));
            }

            return mapper.find(criteria);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }
}

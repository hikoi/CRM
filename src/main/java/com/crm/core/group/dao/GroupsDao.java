package com.crm.core.group.dao;

import com.crm.core.group.consts.GroupType;
import com.crm.core.group.dao.mapper.GroupsMapper;
import com.crm.core.group.entity.Groups;
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
import org.wah.doraemon.utils.IDGenerator;
import org.wah.doraemon.utils.mybatis.Criteria;
import org.wah.doraemon.utils.mybatis.Restrictions;

import java.util.Date;
import java.util.List;

@Repository
public class GroupsDao{

    private Logger logger = LoggerFactory.getLogger(GroupsDao.class);

    @Autowired
    private GroupsMapper mapper;

    public void saveOrUpdate(Groups group){
        try{
            Assert.notNull(group, "分组信息不能为空");

            if(StringUtils.isBlank(group.getId())){
                Assert.notNull(group.getType(), "分组类型不能为空");

                group.setId(IDGenerator.uuid32());
                group.setState(UsingState.USABLE);
                group.setCreateTime(new Date());
                mapper.save(group);
            }else{
                group.setUpdateTime(new Date());
                mapper.update(group);
            }
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public Groups getById(String id){
        try{
            Assert.hasText(id, "分组ID不能为空");

            Criteria criteria = new Criteria();
            criteria.and(Restrictions.eq("id", id));

            return mapper.get(criteria);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public Page<Groups> page(PageRequest pageRequest, String companyId, String name, GroupType type, UsingState state){
        try{
            Assert.notNull(pageRequest, "分页信息不能为空");

            Criteria criteria = new Criteria();
            criteria.limit(Restrictions.limit(pageRequest.getOffset(), pageRequest.getPageSize()));
            criteria.sort(Restrictions.desc("createTime"));

            if(StringUtils.isNotBlank(companyId)){
                criteria.and(Restrictions.eq("companyId", companyId));
            }
            if(StringUtils.isNotBlank(name)){
                criteria.and(Restrictions.like("name", name));
            }
            if(state != null){
                criteria.and(Restrictions.eq("state", state.getId()));
            }
            if(type != null){
                criteria.and(Restrictions.eq("type", type.getId()));
            }

            List<Groups> list = mapper.find(criteria);
            Long total = mapper.count(criteria);

            return new Page<Groups>(list, total, pageRequest);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public List<Groups> find(List<String> ids, String companyId, String name, GroupType type, UsingState state){
        try{
            Criteria criteria = new Criteria();

            if(ids != null && !ids.isEmpty()){
                criteria.and(Restrictions.in("id", ids));
            }
            if(StringUtils.isNotBlank(companyId)){
                criteria.and(Restrictions.eq("companyId", companyId));
            }
            if(StringUtils.isNotBlank(name)){
                criteria.and(Restrictions.like("name", name));
            }
            if(state != null){
                criteria.and(Restrictions.eq("state", state.getId()));
            }
            if(type != null){
                criteria.and(Restrictions.eq("type", type.getId()));
            }

            return mapper.find(criteria);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }
}

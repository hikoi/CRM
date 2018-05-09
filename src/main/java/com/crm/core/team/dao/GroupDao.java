package com.crm.core.team.dao;

import com.crm.core.team.dao.mapper.GroupMapper;
import com.crm.core.team.entity.Group;
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
public class GroupDao{

    private Logger logger = LoggerFactory.getLogger(GroupDao.class);

    @Autowired
    private GroupMapper mapper;

    public void saveOrUpdate(Group group){
        try{
            Assert.notNull(group, "分组信息不能为空");

            if(StringUtils.isBlank(group.getId())){
                group.setId(IDGenerator.uuid32());
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

    public Group getById(String id){
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

    public Page<Group> page(PageRequest pageRequest, String id, String name, UsingState state){
        try{
            Assert.notNull(pageRequest, "分页信息不能为空");

            Criteria criteria = new Criteria();
            criteria.limit(Restrictions.limit(pageRequest.getOffset(), pageRequest.getPageSize()));
            criteria.sort(Restrictions.desc("createTime"));

            if(StringUtils.isNotBlank(id)){
                criteria.and(Restrictions.eq("id", id));
            }
            if(StringUtils.isNotBlank(name)){
                criteria.and(Restrictions.like("name", name));
            }
            if(state != null){
                criteria.and(Restrictions.eq("state", state.getId()));
            }

            List<Group> list = mapper.find(criteria);
            Long total = mapper.count(criteria);

            return new Page<Group>(list, total, pageRequest);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public List<Group> find(List<String> ids, String name, UsingState state){
        try{
            Criteria criteria = new Criteria();

            if(ids != null && !ids.isEmpty()){
                criteria.and(Restrictions.in("id", ids));
            }
            if(StringUtils.isNotBlank(name)){
                criteria.and(Restrictions.like("name", name));
            }
            if(state != null){
                criteria.and(Restrictions.eq("state", state.getId()));
            }

            return mapper.find(criteria);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }
}

package com.crm.core.permission.dao;

import com.crm.core.permission.dao.mapper.FunctionMapper;
import com.crm.core.permission.entity.Function;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMethod;
import org.wah.doraemon.security.exception.DataAccessException;
import org.wah.doraemon.security.request.Page;
import org.wah.doraemon.security.request.PageRequest;
import org.wah.doraemon.utils.IDGenerator;
import org.wah.doraemon.utils.mybatis.Criteria;
import org.wah.doraemon.utils.mybatis.Restrictions;

import java.util.Date;
import java.util.List;

@Repository
public class FunctionDao{

    private Logger logger = LoggerFactory.getLogger(FunctionDao.class);

    @Autowired
    private FunctionMapper mapper;

    public void saveOrUpdate(Function function){
        try{
            Assert.notNull(function, "功能信息不能为空");

            if(StringUtils.isBlank(function.getId())){
                Assert.hasText(function.getUrl(), "功能路径不能为空");
                Assert.notNull(function.getMethod(), "功能请求类型不能为空");

                function.setId(IDGenerator.uuid32());
                function.setNeedAllot(true);
                function.setCreateTime(new Date());
                mapper.save(function);
            }else{
                function.setUpdateTime(new Date());
                mapper.update(function);
            }
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public void saveList(List<Function> functions){
        try{
            Assert.notEmpty(functions, "功能列表不能为空");

            final Date now = new Date();

            for(Function function : functions){
                Assert.hasText(function.getUrl(), "功能路径不能为空");
                Assert.notNull(function.getMethod(), "功能请求类型不能为空");

                function.setId(IDGenerator.uuid32());
                function.setNeedAllot(true);
                function.setCreateTime(now);
            }

            mapper.saveList(functions);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public void updateList(List<Function> functions){
        try{
            Assert.notEmpty(functions, "功能列表不能为空");

            final Date now = new Date();

            for(Function function : functions){
                Assert.hasText(function.getId(), "功能ID不能为空");

                function.setUpdateTime(now);
            }

            mapper.updateList(functions);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public List<Function> find(String url, String description, RequestMethod method, Boolean needAllot, List<String> ids){
        try{
            Criteria criteria = new Criteria();
            criteria.sort(Restrictions.asc("url"));

            if(StringUtils.isNotBlank(url)){
                criteria.and(Restrictions.like("url", url));
            }
            if(StringUtils.isNotBlank(description)){
                criteria.and(Restrictions.like("description", description));
            }
            if(method != null){
                criteria.and(Restrictions.eq("method", method.name()));
            }
            if(needAllot != null){
                criteria.and(Restrictions.eq("needAllot", needAllot));
            }
            if(ids != null && !ids.isEmpty()){
                criteria.and(Restrictions.in("id", ids));
            }

            return mapper.find(criteria);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public List<Function> findByNeedAllot(Boolean needAllot){
        try{
            Assert.notNull(needAllot, "功能是否需要授权标示不能为空");

            Criteria criteria = new Criteria();
            criteria.and(Restrictions.eq("needAllot", needAllot));

            return mapper.find(criteria);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public Function getById(String id){
        try{
            Assert.hasText(id, "功能ID不能为空");

            Criteria criteria = new Criteria();
            criteria.and(Restrictions.eq("id", id));

            return mapper.get(criteria);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }
}

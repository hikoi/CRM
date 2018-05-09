package com.crm.core.sensitive.dao;

import com.crm.core.sensitive.dao.mapper.SensitiveMapper;
import com.crm.core.sensitive.entity.Sensitive;
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
public class SensitiveDao{

    private Logger logger = LoggerFactory.getLogger(SensitiveDao.class);

    @Autowired
    private SensitiveMapper mapper;

    public void saveOrUpdate(Sensitive sensitive){
        try{
            Assert.notNull(sensitive, "敏感词信息不能为空");

            if(StringUtils.isBlank(sensitive.getId())){
                sensitive.setId(IDGenerator.uuid32());
                sensitive.setCreateTime(new Date());
                mapper.save(sensitive);
            }else{
                sensitive.setUpdateTime(new Date());
                mapper.update(sensitive);
            }
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public Sensitive getById(String id){
        try{
            Assert.hasText(id, "敏感词ID不能为空");

            Criteria criteria = new Criteria();
            criteria.and(Restrictions.eq("id", id));

            return mapper.get(criteria);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public Page<Sensitive> page(PageRequest pageRequest, String content, UsingState state){
        try{
            Assert.notNull(pageRequest, "分页信息不能为空");

            Criteria criteria = new Criteria();
            criteria.limit(Restrictions.limit(pageRequest.getOffset(), pageRequest.getPageSize()));
            criteria.sort(Restrictions.desc("createTime"));

            if(StringUtils.isNotBlank(content)){
                criteria.and(Restrictions.like("content", content));
            }
            if(state != null){
                criteria.and(Restrictions.eq("state", state));
            }

            List<Sensitive> list = mapper.find(criteria);
            Long total = mapper.count(criteria);

            return new Page<Sensitive>(list, total, pageRequest);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }
}

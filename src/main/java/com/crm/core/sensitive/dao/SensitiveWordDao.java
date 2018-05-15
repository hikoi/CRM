package com.crm.core.sensitive.dao;

import com.crm.core.sensitive.dao.mapper.SensitiveWordMapper;
import com.crm.core.sensitive.entity.SensitiveWord;
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
public class SensitiveWordDao{

    private Logger logger = LoggerFactory.getLogger(SensitiveWordDao.class);

    @Autowired
    private SensitiveWordMapper mapper;

    public void saveOrUpdate(SensitiveWord sensitiveWord){
        try{
            Assert.notNull(sensitiveWord, "敏感词信息不能为空");

            if(StringUtils.isBlank(sensitiveWord.getId())){
                sensitiveWord.setId(IDGenerator.uuid32());
                sensitiveWord.setCreateTime(new Date());
                mapper.save(sensitiveWord);
            }else{
                sensitiveWord.setUpdateTime(new Date());
                mapper.update(sensitiveWord);
            }
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public void updateRelationByGroupId(String groupId, List<String> sensitiveIds){
        try{
            Assert.hasText(groupId, "分组ID不能为空");

            //删除原有关系
            mapper.dissolveByGroupId(groupId);

            //保存新关系
            if(sensitiveIds != null && !sensitiveIds.isEmpty()){
                mapper.establishByGroupId(groupId, sensitiveIds);
            }
        }catch(Exception e){
            logger.error(e.getMessage(), e);
        }
    }

    public void updateRelationByWechatId(String wechatId, List<String> sensitiveIds){
        try{
            Assert.hasText(wechatId, "微信ID不能为空");

            //删除原有关系
            mapper.dissolveByWechatId(wechatId);

            //保存新关系
            if(sensitiveIds != null && !sensitiveIds.isEmpty()){
                mapper.establishByWechatId(wechatId, sensitiveIds);
            }
        }catch(Exception e){
            logger.error(e.getMessage(), e);
        }
    }

    public SensitiveWord getById(String id){
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

    public Page<SensitiveWord> page(PageRequest pageRequest, String content, UsingState state, String groupId){
        try{
            Assert.notNull(pageRequest, "分页信息不能为空");

            Criteria criteria = new Criteria();
            criteria.limit(Restrictions.limit(pageRequest.getOffset(), pageRequest.getPageSize()));
            criteria.sort(Restrictions.desc("s.createTime"));

            if(StringUtils.isNotBlank(content)){
                criteria.and(Restrictions.like("s.content", content));
            }
            if(state != null){
                criteria.and(Restrictions.eq("s.state", state));
            }
            if(StringUtils.isNotBlank(groupId)){
                criteria.and(Restrictions.eq("wg.group_id", groupId));
            }

            List<SensitiveWord> list = mapper.find(criteria);
            Long total = mapper.count(criteria);

            return new Page<SensitiveWord>(list, total, pageRequest);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public List<SensitiveWord> findByWxno(String wxno, UsingState state){
        try{
            Assert.hasText(wxno, "微信号不能为空");

            Criteria criteria = new Criteria();
            criteria.and(Restrictions.eq("w.wxno", wxno));

            if(state != null){
                criteria.and(Restrictions.eq("s.state", state.getId()));
            }

            return mapper.find(criteria);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }
}

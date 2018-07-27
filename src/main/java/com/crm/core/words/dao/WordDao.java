package com.crm.core.words.dao;

import com.crm.core.words.dao.mapper.WordMapper;
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
import org.wah.doraemon.utils.IDGenerator;
import org.wah.doraemon.utils.mybatis.Criteria;
import org.wah.doraemon.utils.mybatis.Restrictions;

import java.util.Date;
import java.util.List;

@Repository
public class WordDao{

    private Logger logger = LoggerFactory.getLogger(WordDao.class);

    @Autowired
    private WordMapper mapper;

    public void saveOrUpdate(Word word){
        try{
            Assert.notNull(word, "敏感词信息不能为空");

            if(StringUtils.isBlank(word.getId())){
                Assert.hasText(word.getContent(), "敏感词内容不能为空");

                word.setId(IDGenerator.uuid32());
                word.setState(UsingState.USABLE);
                word.setCreateTime(new Date());
                mapper.save(word);
            }else{
                word.setUpdateTime(new Date());
                mapper.update(word);
            }
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public Page<Word> page(PageRequest pageRequest, String content, UsingState state){
        try{
            Assert.notNull(pageRequest, "分页信息不能为空");

            Criteria criteria = new Criteria();
            criteria.limit(Restrictions.limit(pageRequest.getOffset(), pageRequest.getPageSize()));
            criteria.sort(Restrictions.desc("content"));

            if(StringUtils.isNotBlank(content)){
                criteria.and(Restrictions.like("content", content));
            }
            if(state != null){
                criteria.and(Restrictions.eq("state", state.getId()));
            }

            List<Word> list  = mapper.find(criteria);
            Long            total = mapper.count(criteria);

            return new Page<Word>(list, total, pageRequest);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }
}

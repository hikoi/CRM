package com.crm.core.sensitive.dao;

import com.crm.core.sensitive.dao.mapper.SensitiveMapper;
import com.crm.core.sensitive.entity.Sensitive;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import org.wah.doraemon.security.exception.DataAccessException;
import org.wah.doraemon.utils.IDGenerator;

import java.util.Date;

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
}

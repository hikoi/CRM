package com.crm.core.account.dao;

import com.crm.core.account.dao.mapper.AccountGroupMapper;
import com.crm.core.account.entity.AccountGroup;
import org.apache.commons.lang3.StringUtils;
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

@Repository
public class AccountGroupDao{

    private Logger logger = LoggerFactory.getLogger(AccountGroupDao.class);

    @Autowired
    private AccountGroupMapper mapper;

    public void saveOrUpdate(AccountGroup group){
        try{
            Assert.notNull(group, "账户分组信息不能为空");

            if(StringUtils.isBlank(group.getId())){
                group.setId(IDGenerator.uuid32());
                group.setCreateTime(new Date());
                group.setState(UsingState.USABLE);
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

    public AccountGroup getById(String id){
        try{
            Assert.hasText(id, "账户分组ID不能为空");

            Criteria criteria = new Criteria();
            criteria.and(Restrictions.eq("id", id));

            return mapper.get(criteria);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }
}

package com.crm.core.account.dao;

import com.crm.core.account.dao.mapper.AccountMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import org.wah.doraemon.entity.Account;
import org.wah.doraemon.security.exception.DataAccessException;
import org.wah.doraemon.security.request.Page;
import org.wah.doraemon.security.request.PageRequest;
import org.wah.doraemon.utils.IDGenerator;
import org.wah.doraemon.utils.mybatis.Criteria;
import org.wah.doraemon.utils.mybatis.Restrictions;

import java.util.Date;
import java.util.List;

@Repository
public class AccountDao{

    private Logger logger = LoggerFactory.getLogger(AccountDao.class);

    @Autowired
    private AccountMapper mapper;

    public void saveOrUpdate(Account account){
        try{
            Assert.notNull(account, "账户信息不能为空");

            if(StringUtils.isBlank(account.getId())){
                Assert.hasText(account.getUsername(), "账户登录名不能为空");
                Assert.hasText(account.getPassword(), "账户登录密码不能为空");

                account.setId(IDGenerator.uuid32());
                account.setIsDelete(false);
                account.setCreateTime(new Date());
                mapper.save(account);
            }else{
                account.setUpdateTime(new Date());
                mapper.update(account);
            }
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public Account getByUsername(String username){
        try{
            Assert.hasText(username, "账户登录名不能为空");

            Criteria criteria = new Criteria();
            criteria.and(Restrictions.eq("username", username));
            criteria.and(Restrictions.eq("isDelete", false));

            return mapper.get(criteria);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public boolean existByUsername(String username){
        try{
            Assert.hasText(username, "账户登录名不能为空");

            Criteria criteria = new Criteria();
            criteria.and(Restrictions.eq("username", username));

            Long total = mapper.count(criteria);

            return (total != null && total > 0);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }
}

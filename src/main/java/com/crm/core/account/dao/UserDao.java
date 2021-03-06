package com.crm.core.account.dao;

import com.crm.core.account.dao.mapper.UserMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import org.wah.doraemon.entity.User;
import org.wah.doraemon.entity.consts.Sex;
import org.wah.doraemon.security.exception.DataAccessException;
import org.wah.doraemon.security.request.Page;
import org.wah.doraemon.security.request.PageRequest;
import org.wah.doraemon.utils.IDGenerator;
import org.wah.doraemon.utils.mybatis.Criteria;
import org.wah.doraemon.utils.mybatis.Restrictions;

import java.util.Date;
import java.util.List;

@Repository
public class UserDao{

    private Logger logger = LoggerFactory.getLogger(UserDao.class);

    @Autowired
    private UserMapper mapper;

    public void saveOrUpdate(User user){
        try{
            Assert.notNull(user, "用户信息不能为空");

            if(StringUtils.isBlank(user.getId())){
                Assert.hasText(user.getAccountId(), "用户账户ID不能为空");

                user.setId(IDGenerator.uuid32());
                user.setCreateTime(new Date());
                mapper.save(user);
            }else{
                user.setUpdateTime(new Date());
                mapper.update(user);
            }
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public User getByAccountId(String accountId){
        try{
            Assert.hasText(accountId, "用户账户ID不能为空");

            Criteria criteria = new Criteria();
            criteria.and(Restrictions.eq("accountId", accountId));

            return mapper.get(criteria);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public User getWithAccountByAccountId(String accountId){
        try{
            Assert.hasText(accountId, "用户账户ID不能为空");

            Criteria criteria = new Criteria();
            criteria.and(Restrictions.eq("u.accountId", accountId));

            return mapper.getWithAccount(criteria);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public Page<User> page(PageRequest pageRequest, String name, List<String> accountIds){
        try{
            Assert.notNull(pageRequest, "分页信息不能为空");

            Criteria criteria = new Criteria();
            criteria.limit(Restrictions.limit(pageRequest.getOffset(), pageRequest.getPageSize()));

            if(StringUtils.isNotBlank(name)){
                criteria.and(Restrictions.like("name", name));
            }
            if(accountIds != null && !accountIds.isEmpty()){
                criteria.and(Restrictions.in("accountId", accountIds));
            }

            List<User> list  = mapper.find(criteria);
            Long       total = mapper.count(criteria);

            return new Page<User>(list, total, pageRequest);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public Page<User> pageWithAccount(PageRequest pageRequest, String name, String username, Boolean isInternal,
                                      List<String> accountIds){
        try{
            Assert.notNull(pageRequest, "分页信息不能为空");

            Criteria criteria = new Criteria();
            criteria.limit(Restrictions.limit(pageRequest.getOffset(), pageRequest.getPageSize()));
            criteria.and(Restrictions.eq("a.isDelete", false));

            if(StringUtils.isNotBlank(name)){
                criteria.and(Restrictions.like("u.name", name));
            }
            if(StringUtils.isNotBlank(username)){
                criteria.and(Restrictions.like("a.username", username));
            }
            if(isInternal != null){
                criteria.and(Restrictions.eq("a.isInternal", isInternal));
            }
            if(accountIds != null && !accountIds.isEmpty()){
                criteria.and(Restrictions.in("u.accountId", accountIds));
            }

            List<User> list  = mapper.findWithAccount(criteria);
            Long       total = mapper.countWithAccount(criteria);

            return new Page<User>(list, total, pageRequest);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }
}

package com.crm.core.permission.dao;

import com.crm.core.permission.dao.mapper.AccountRoleMapper;
import com.crm.core.permission.entity.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import org.wah.doraemon.entity.Account;
import org.wah.doraemon.entity.consts.UsingState;
import org.wah.doraemon.security.exception.DataAccessException;
import org.wah.doraemon.utils.mybatis.Criteria;
import org.wah.doraemon.utils.mybatis.Restrictions;

import java.util.List;

@Repository
public class AccountRoleDao{

    private Logger logger = LoggerFactory.getLogger(AccountRoleDao.class);

    @Autowired
    private AccountRoleMapper mapper;

    public void save(String accountId, String roleId){
        try{
            Assert.hasText(accountId, "账户ID不能为空");
            Assert.hasText(roleId, "角色ID不能为空");

            mapper.save(accountId, roleId);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public void saveListByAccountId(String accountId, List<String> roleIds){
        try{
            Assert.hasText(accountId, "账户ID不能为空");
            Assert.notEmpty(roleIds, "角色ID列表不能为空");

            mapper.saveListByAccountId(accountId, roleIds);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public void saveListByRoleId(String roleId, List<String> accountIds){
        try{
            Assert.hasText(roleId, "角色ID不能为空");
            Assert.notEmpty(accountIds, "账户ID列表不能为空");

            mapper.saveListByRoleId(roleId, accountIds);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public void deleteByAccountId(String accountId){
        try{
            Assert.hasText(accountId, "账户ID不能为空");

            Criteria criteria = new Criteria();
            criteria.and(Restrictions.eq("accountId", accountId));

            mapper.delete(criteria);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public void deleteByRoleId(String roleId){
        try{
            Assert.hasText(roleId, "角色ID不能为空");

            Criteria criteria = new Criteria();
            criteria.and(Restrictions.eq("roleId", roleId));

            mapper.delete(criteria);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public List<Role> findRoles(List<String> accountIds, UsingState roleState){
        try{
            Criteria criteria = new Criteria();
            criteria.and(Restrictions.eq("a.isDelete", false));

            if(accountIds != null && !accountIds.isEmpty()){
                criteria.and(Restrictions.in("ar.accountId",accountIds));
            }
            if(roleState != null){
                criteria.and(Restrictions.eq("r.state", roleState.getId()));
            }

            return mapper.findRoles(criteria);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public List<Account> findAccounts(List<String> roleIds){
        try{
            Criteria criteria = new Criteria();
            criteria.and(Restrictions.eq("a.isDelete", false));

            if(roleIds != null && !roleIds.isEmpty()){
                criteria.and(Restrictions.in("ar.roleId", roleIds));
            }

            return mapper.findAccounts(criteria);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }
}

package com.crm.core.permission.dao;

import com.crm.core.permission.consts.ResourceType;
import com.crm.core.permission.dao.mapper.AccountPermissionMapper;
import com.crm.core.permission.entity.Permission;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import org.wah.doraemon.security.exception.DataAccessException;
import org.wah.doraemon.utils.mybatis.Criteria;
import org.wah.doraemon.utils.mybatis.Restrictions;

import java.util.List;

@Repository
public class AccountPermissionDao{

    private Logger logger = LoggerFactory.getLogger(AccountPermissionDao.class);

    @Autowired
    private AccountPermissionMapper mapper;

    public void save(String accountId, String permissionId){
        try{
            Assert.hasText(accountId, "账户ID不能为空");
            Assert.hasText(permissionId, "权限ID不能为空");

            mapper.save(accountId, permissionId);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public void saveList(String accountId, List<String> permissionIds){
        try{
            Assert.hasText(accountId, "账户ID不能为空");
            Assert.notEmpty(permissionIds, "权限ID列表不能为空");

            mapper.saveList(accountId, permissionIds);
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

    public void deleteByPermissionId(String permissionId){
        try{
            Assert.hasText(permissionId, "权限ID不能为空");

            Criteria criteria = new Criteria();
            criteria.and(Restrictions.eq("permissionId", permissionId));

            mapper.delete(criteria);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public List<Permission> find(List<String> accountIds, ResourceType type){
        try{
            Criteria criteria = new Criteria();
            criteria.and(Restrictions.eq("a.isDelete", false));

            if(accountIds != null && !accountIds.isEmpty()){
                criteria.and(Restrictions.in("ap.accountId", accountIds));
            }
            if(type != null){
                criteria.and(Restrictions.eq("p.type", type.getId()));
            }

            return mapper.find(criteria);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }
}

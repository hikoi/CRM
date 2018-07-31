package com.crm.core.permission.dao;

import com.crm.core.permission.consts.ResourceType;
import com.crm.core.permission.dao.mapper.RolePermissionMapper;
import com.crm.core.permission.entity.Permission;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import org.wah.doraemon.entity.consts.UsingState;
import org.wah.doraemon.security.exception.DataAccessException;
import org.wah.doraemon.utils.mybatis.Criteria;
import org.wah.doraemon.utils.mybatis.Restrictions;

import java.util.List;

@Repository
public class RolePermissionDao{

    private Logger logger = LoggerFactory.getLogger(RolePermissionDao.class);

    @Autowired
    private RolePermissionMapper mapper;

    public void save(String roleId, String permissionId){
        try{
            Assert.hasText(roleId, "角色ID不能为空");
            Assert.hasText(permissionId, "权限ID不能为空");

            mapper.save(roleId, permissionId);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public void saveList(String roleId, List<String> permissionIds){
        try{
            Assert.hasText(roleId, "角色ID不能为空");
            Assert.notEmpty(permissionIds, "权限ID列表不能为空");

            mapper.saveList(roleId, permissionIds);
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

    public List<Permission> find(List<String> roleIds, ResourceType type, UsingState roleState){
        try{
            Criteria criteria = new Criteria();

            if(roleIds != null && !roleIds.isEmpty()){
                criteria.and(Restrictions.in("rp.roleId", roleIds));
            }
            if(type != null){
                criteria.and(Restrictions.eq("p.type", type.getId()));
            }
            if(roleState != null){
                criteria.and(Restrictions.eq("r.state", roleState.getId()));
            }

            return mapper.find(criteria);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }
}

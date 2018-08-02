package com.crm.core.permission.dao;

import com.crm.core.permission.consts.ResourceType;
import com.crm.core.permission.dao.mapper.PermissionMapper;
import com.crm.core.permission.entity.Permission;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import org.wah.doraemon.security.exception.DataAccessException;
import org.wah.doraemon.utils.IDGenerator;
import org.wah.doraemon.utils.ObjectUtils;
import org.wah.doraemon.utils.mybatis.Criteria;
import org.wah.doraemon.utils.mybatis.Restrictions;

import java.util.Date;
import java.util.List;

@Repository
public class PermissionDao{

    private Logger logger = LoggerFactory.getLogger(PermissionDao.class);

    @Autowired
    private PermissionMapper mapper;

    public void save(Permission permission){
        try{
            Assert.notNull(permission, "权限信息不能为空");
            Assert.hasText(permission.getResourceId(), "资源ID不能为空");
            Assert.notNull(permission.getType(), "权限类型不能为空");

            permission.setId(IDGenerator.uuid32());
            permission.setCreateTime(new Date());
            mapper.save(permission);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public void saveList(List<Permission> permissions){
        try{
            Assert.notEmpty(permissions, "权限列表不能为空");

            final Date now = new Date();

            for(Permission permission : permissions){
                Assert.notNull(permission, "权限信息不能为空");
                Assert.hasText(permission.getResourceId(), "资源ID不能为空");
                Assert.notNull(permission.getType(), "权限类型不能为空");

                permission.setId(IDGenerator.uuid32());
                permission.setCreateTime(now);
            }

            mapper.saveList(permissions);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public Permission getByResourceIdAndType(String resourceId, ResourceType type){
        try{
            Assert.hasText(resourceId, "资源ID不能为空");
            Assert.notNull(type, "资源类型不能为空");

            Criteria criteria = new Criteria();
            criteria.and(Restrictions.eq("resourceId", resourceId));
            criteria.and(Restrictions.eq("type", type.getId()));

            return mapper.get(criteria);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public List<Permission> findByType(ResourceType type){
        try{
            Assert.notNull(type, "权限类型不能为空");

            Criteria criteria = new Criteria();
            criteria.and(Restrictions.eq("type", type.getId()));

            return mapper.find(criteria);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public List<Permission> findByTypes(List<ResourceType> types){
        try{
            Assert.notEmpty(types, "权限类型列表不能为空");

            Criteria criteria = new Criteria();
            criteria.and(Restrictions.in("type", ObjectUtils.properties(types, "id", Integer.class)));

            return mapper.find(criteria);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }
}

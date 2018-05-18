package com.crm.core.account.dao.mapper;

import com.crm.core.account.entity.Permission;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import org.wah.doraemon.security.exception.DataAccessException;
import org.wah.doraemon.utils.IDGenerator;
import org.wah.doraemon.utils.mybatis.Criteria;
import org.wah.doraemon.utils.mybatis.Restrictions;

import java.util.Date;

@Repository
public class PermissionDao{

    private Logger logger = LoggerFactory.getLogger(PermissionDao.class);

    @Autowired
    private PermissionMapper mapper;

    public void saveOrUpdate(Permission permission){
        try{
            Assert.notNull(permission, "权限信息不能为空");

            if(StringUtils.isBlank(permission.getId())){
                Assert.hasText(permission.getUrl(), "权限路径不能为空");

                permission.setId(IDGenerator.uuid32());
                permission.setNeedAllot(true);
                permission.setCreateTime(new Date());
                mapper.save(permission);
            }else{
                permission.setUpdateTime(new Date());
                mapper.update(permission);
            }
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public Permission getById(String id){
        try{
            Assert.hasText(id, "权限ID不能为空");

            Criteria criteria = new Criteria();
            criteria.and(Restrictions.eq("id", id));

            return mapper.get(criteria);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }
}

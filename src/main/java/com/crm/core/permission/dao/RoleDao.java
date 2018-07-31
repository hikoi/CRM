package com.crm.core.permission.dao;

import com.crm.core.permission.dao.mapper.RoleMapper;
import com.crm.core.permission.entity.Role;
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
public class RoleDao{

    private Logger logger = LoggerFactory.getLogger(RoleDao.class);

    @Autowired
    private RoleMapper mapper;

    public void saveOrUpdate(Role role){
        try{
            Assert.notNull(role, "权限信息不能为空");

            if(StringUtils.isBlank(role.getId())){
                role.setId(IDGenerator.uuid32());
                role.setState(UsingState.USABLE);
                role.setCreateTime(new Date());

                mapper.save(role);
            }else{
                role.setUpdateTime(new Date());
                mapper.update(role);
            }
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public Role getById(String id){
        try{
            Assert.hasText(id, "角色ID不能为空");

            Criteria criteria = new Criteria();
            criteria.and(Restrictions.eq("id", id));

            return mapper.get(criteria);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }
}

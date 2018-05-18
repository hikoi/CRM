package com.crm.core.account.dao.mapper;

import com.crm.core.account.entity.Permission;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.wah.doraemon.utils.mybatis.Criteria;

import java.util.List;

@Repository
public interface PermissionMapper{

    void save(Permission permission);

    void update(Permission permission);

    Permission get(@Param("params") Criteria criteria);

    List<Permission> find(@Param("params") Criteria criteria);

    Long count(@Param("params") Criteria criteria);
}

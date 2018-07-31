package com.crm.core.permission.dao.mapper;

import com.crm.core.permission.entity.Permission;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.wah.doraemon.utils.mybatis.Criteria;

import java.util.List;

@Repository
public interface PermissionMapper{

    void save(Permission permission);

    void saveList(@Param("permissions") List<Permission> permissions);

    Permission get(@Param("params") Criteria criteria);
}

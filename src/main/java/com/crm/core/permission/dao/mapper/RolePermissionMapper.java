package com.crm.core.permission.dao.mapper;

import com.crm.core.permission.entity.Permission;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.wah.doraemon.utils.mybatis.Criteria;

import java.util.List;

@Repository
public interface RolePermissionMapper{

    void save(@Param("roleId") String roleId, @Param("permissionId") String permissionId);

    void saveList(@Param("roleId") String roleId, @Param("permissionIds") List<String> permissionIds);

    void delete(@Param("params") Criteria criteria);

    List<Permission> find(@Param("params") Criteria criteria);
}

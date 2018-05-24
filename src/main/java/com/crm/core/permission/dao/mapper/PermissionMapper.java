package com.crm.core.permission.dao.mapper;

import com.crm.core.permission.consts.ResourceType;
import com.crm.core.permission.entity.Permission;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.wah.doraemon.utils.mybatis.Criteria;

import java.util.List;
import java.util.Set;

@Repository
public interface PermissionMapper{

    void save(Permission permission);

    void saveList(@Param("permissions") List<Permission> permissions);

    void saveRelationsToRole(@Param("permissionIds") List<String> permissionIds, @Param("roleId") String roleId);

    void deleteRelationsToRole(@Param("roleId") String roleId, @Param("type") ResourceType type);

    void saveRelationToAccount(@Param("permissionIds") List<String> permissionIds, @Param("accountId") String accountId);

    void deleteRelationToAccount(@Param("accountId") String accountId, @Param("type") ResourceType type);

    Permission get(@Param("params") Criteria criteria);

    List<Permission> find(@Param("params") Criteria criteria);

    Set<Permission> findByRoleIds(@Param("params") Criteria criteria);

    Set<Permission> findByAccountId(@Param("params") Criteria criteria);
}

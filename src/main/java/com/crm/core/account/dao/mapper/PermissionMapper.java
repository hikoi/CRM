package com.crm.core.account.dao.mapper;

import com.crm.core.account.entity.Permission;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.wah.doraemon.utils.mybatis.Criteria;

import java.util.List;
import java.util.Set;

@Repository
public interface PermissionMapper{

    void save(Permission permission);

    void saveList(@Param("permissions") List<Permission> permissions);

    void update(Permission permission);

    void updateList(@Param("permissions") List<Permission> permissions);

    Permission get(@Param("params") Criteria criteria);

    List<Permission> find(@Param("params") Criteria criteria);

    Long count(@Param("params") Criteria criteria);

    void establishByAccountId(@Param("accountId") String accountId, @Param("permissionIds") List<String> permissionIds);

    void dissolveByAccountId(@Param("accountId") String accountId);

    List<Permission> findByAccountId(String accountId);

    List<Permission> findByRoleIds(@Param("roleIds") List<String> roleIds);
}

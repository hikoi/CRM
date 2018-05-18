package com.crm.core.account.dao.mapper;

import com.crm.core.account.entity.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.wah.doraemon.utils.mybatis.Criteria;

import java.util.List;

@Repository
public interface RoleMapper{

    void save(Role role);

    void update(Role role);

    Role get(@Param("params") Criteria criteria);

    List<Role> find(@Param("params") Criteria criteria);

    Long count(@Param("params") Criteria criteria);

    void establishByRoleId(@Param("roleId") String roleId, @Param("permissionIds") List<String> permissionIds);

    void dissolveByRoleId(@Param("roleId") String roleId);

    void establishByAccountId(@Param("accountId") String accountId, @Param("roleIds") List<String> roleIds);

    void dissolveByAccountId(@Param("accountId") String accountId);

    List<Role> findByAccountId(String accountId);
}

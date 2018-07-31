package com.crm.core.permission.dao.mapper;

import com.crm.core.permission.entity.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.wah.doraemon.entity.Account;
import org.wah.doraemon.utils.mybatis.Criteria;

import java.util.List;

@Repository
public interface AccountRoleMapper{

    void save(@Param("accountId") String accountId, @Param("roleId") String role);

    void saveListByAccountId(@Param("accountId") String accountId, @Param("roleIds") List<String> roleIds);

    void saveListByRoleId(@Param("roleId") String roleId, @Param("accountIds") List<String> accountIds);

    void delete(@Param("params") Criteria criteria);

    List<Role> findRoles(@Param("params") Criteria criteria);

    List<Account> findAccounts(@Param("params") Criteria criteria);
}

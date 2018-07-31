package com.crm.core.permission.dao.mapper;

import com.crm.core.permission.entity.Permission;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.wah.doraemon.utils.mybatis.Criteria;

import java.util.List;

@Repository
public interface AccountPermissionMapper{

    void save(@Param("accountId") String accountId, @Param("permissionId") String permissionId);

    void saveList(@Param("accountId") String accountId, @Param("permissionIds") List<String> permissionIds);

    void delete(@Param("params") Criteria criteria);

    List<Permission> find(@Param("params") Criteria criteria);
}

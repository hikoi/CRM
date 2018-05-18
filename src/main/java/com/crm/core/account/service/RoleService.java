package com.crm.core.account.service;

import com.crm.core.account.entity.Role;

import java.util.List;

public interface RoleService{

    void save(Role role);

    void updateRelationByRoleId(String roleId, List<String> permissionIds);

    void updateRelationByAccountId(String accountId, List<String> roleIds);
}

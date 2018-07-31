package com.crm.core.permission.service;

import java.util.List;

public interface RolePermissionService{

    void save(String roleId, String permissionId);

    void saveList(String roleId, List<String> permissionIds);
}

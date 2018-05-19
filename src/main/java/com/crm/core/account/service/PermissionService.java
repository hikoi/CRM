package com.crm.core.account.service;

import com.crm.core.account.entity.Permission;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Set;

public interface PermissionService{

    void save(Permission permission);

    void synchronize();

    void updateRelationByAccountId(String accountId, List<String> permissionIds);

    Set<Permission> findByAccountId(String accountId);

    List<Permission> find(String url, RequestMethod method, Boolean needAllot);
}

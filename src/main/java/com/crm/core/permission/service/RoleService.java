package com.crm.core.permission.service;

import com.crm.core.permission.entity.Role;
import org.wah.doraemon.entity.consts.UsingState;
import org.wah.doraemon.security.request.Page;
import org.wah.doraemon.security.request.PageRequest;

public interface RoleService{

    void save(Role role);

    void update(Role role);

    Role getById(String id);
}

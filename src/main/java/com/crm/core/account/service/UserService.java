package com.crm.core.account.service;

import org.wah.doraemon.entity.User;
import org.wah.doraemon.security.request.Page;
import org.wah.doraemon.security.request.PageRequest;

public interface UserService{

    Page<User> page(PageRequest pageRequest, String username, String name, String companyId, String departmentId,
                    String positionId);
}

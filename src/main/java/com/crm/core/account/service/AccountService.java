package com.crm.core.account.service;

import org.wah.doraemon.entity.consts.Sex;

public interface AccountService{

    void save(String username, String password, String nickname, String name, Sex sex, String companyId,
              String departmentId, String positionId);

    String login(String username, String password);
}

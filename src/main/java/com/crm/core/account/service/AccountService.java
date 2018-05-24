package com.crm.core.account.service;

import org.wah.doraemon.entity.consts.Sex;

public interface AccountService{

    void register(String username, String password, String name, String nickname, String headImgUrl,
                  Sex sex, String companyId, String departmentId, String positionId);

    String login(String username, String password);
}

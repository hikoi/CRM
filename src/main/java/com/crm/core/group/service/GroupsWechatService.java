package com.crm.core.group.service;

import java.util.List;

public interface GroupsWechatService{

    void save(String groupsId, String wechatId);

    void saveList(String groupsId, List<String> wechatIds);
}

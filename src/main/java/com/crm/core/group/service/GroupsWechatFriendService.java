package com.crm.core.group.service;

import java.util.List;
import java.util.Map;

public interface GroupsWechatFriendService{

    void save(String newGroupsId, String oldGroupsId, String friendId);

    List<Map<String, Object>> findByTicket(String ticket);
}

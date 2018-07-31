package com.crm.core.group.service;

import com.crm.core.group.entity.GroupsMenu;

import java.util.List;

public interface GroupsMenuService{

    List<GroupsMenu> find(String groupsId, String menuId);
}

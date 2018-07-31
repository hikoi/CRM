package com.crm.core.group.entity;

import com.crm.core.permission.entity.Menu;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GroupsMenu{

    private String groupsId;
    private String groupsName;
    private List<Menu> menus;
}

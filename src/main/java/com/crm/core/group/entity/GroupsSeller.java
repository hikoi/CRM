package com.crm.core.group.entity;

import lombok.Getter;
import lombok.Setter;
import org.wah.doraemon.entity.User;

import java.util.List;

@Getter
@Setter
public class GroupsSeller{

    private String groupsId;
    private String groupsName;
    private List<User> sellers;
}

package com.crm.core.group.entity;

import com.crm.core.wechat.entity.Wechat;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GroupsWechat{

    private String groupsId;
    private String groupsName;
    private List<Wechat> wechats;
}

package com.crm.core.group.entity;

import com.crm.core.wechat.entity.WechatFriend;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class GroupsWechatFriend{

    private String groupsId;
    private String groupsName;
    private List<WechatFriend> friends;
}

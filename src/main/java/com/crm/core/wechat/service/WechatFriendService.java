package com.crm.core.wechat.service;

import com.crm.core.wechat.entity.WechatFriend;

import java.util.List;

public interface WechatFriendService{

    void save(String wechatId, WechatFriend friend);

    void synchronize(String wechatId, List<WechatFriend> friends);
}

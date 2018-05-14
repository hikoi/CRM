package com.crm.core.wechat.service;

import com.crm.core.wechat.entity.WechatFriend;
import org.wah.doraemon.security.request.Page;
import org.wah.doraemon.security.request.PageRequest;

import java.util.List;

public interface WechatFriendService{

    void save(String wechatId, WechatFriend friend);

    void synchronize(String wechatId, List<WechatFriend> friends);

    Page<WechatFriend> page(PageRequest pageRequest, String id, String wechatId, String wxid, String wxno, String nickname);
}

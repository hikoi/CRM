package com.crm.core.wechat.service;

import com.crm.core.wechat.entity.WechatFriend;
import org.wah.doraemon.security.request.Page;
import org.wah.doraemon.security.request.PageRequest;

import java.util.List;

public interface WechatFriendService{

    void save(String wxno, WechatFriend friend);

    void synchronize(String wxno, List<WechatFriend> friends);

    Page<WechatFriend> page(PageRequest pageRequest, String sellerId, String wechatId, String wxid, String wxno, String nickname);

    Page<WechatFriend> pageByTicket(PageRequest pageRequest, String ticket, String wechatId, String wxid, String wxno, String nickname);

    void redistribution(String id, String toAccount);
}

package com.crm.core.wechat.service;

import com.crm.core.wechat.consts.WechatMessageStatus;
import com.crm.core.wechat.consts.WechatMessageType;
import com.crm.core.wechat.entity.WechatMessage;
import org.wah.doraemon.security.request.Page;
import org.wah.doraemon.security.request.PageRequest;

import java.util.List;

public interface WechatMessageService{

    void save(WechatMessage message);

    void saveList(List<WechatMessage> messages);

    Page<WechatMessage> page(PageRequest pageRequest, String accountId, String wechatId,
                             String wxid, WechatMessageType type, WechatMessageStatus status);
}

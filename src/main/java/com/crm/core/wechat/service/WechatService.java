package com.crm.core.wechat.service;

import com.crm.core.wechat.entity.Wechat;
import org.wah.doraemon.security.request.Page;
import org.wah.doraemon.security.request.PageRequest;

public interface WechatService{

    void save(Wechat wechat);

    void update(Wechat wechat);

    Page<Wechat> page(PageRequest pageRequest, String accountId, String wxno, String nickname);
}

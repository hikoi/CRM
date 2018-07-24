package com.crm.core.im.service;

import com.crm.core.im.entity.IMUser;

public interface IMUserService{

    /**
     * 客服端登录
     */
    IMUser getByTicket(String ticket);

    /**
     * 微信APP登录
     */
    IMUser getByWxno(String wxno);
}

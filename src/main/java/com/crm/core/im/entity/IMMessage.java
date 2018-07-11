package com.crm.core.im.entity;

import com.crm.core.im.consts.IMUserType;
import com.crm.core.wechat.consts.WechatMessageType;
import lombok.Getter;
import lombok.Setter;
import org.wah.doraemon.domain.Createable;
import org.wah.doraemon.domain.Entity;
import org.wah.doraemon.domain.Updateable;

import java.util.Date;

@Getter
@Setter
public class IMMessage extends Entity implements Createable, Updateable{

    private String fromAccount;
    private String toAccount;
    private String wxid;

    private IMUserType        senderType;
    private WechatMessageType messageType;

    private Date conversationTime;
    private Date createTime;
    private Date updateTime;
}

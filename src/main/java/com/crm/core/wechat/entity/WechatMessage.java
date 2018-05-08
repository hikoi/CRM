package com.crm.core.wechat.entity;

import com.crm.core.wechat.consts.WechatMessageStatus;
import com.crm.core.wechat.consts.WechatMessageType;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import org.wah.doraemon.domain.Createable;
import org.wah.doraemon.domain.Entity;
import org.wah.doraemon.domain.Updateable;

import java.util.Date;

@Getter
@Setter
public class WechatMessage extends Entity implements Createable, Updateable{

    @SerializedName(value = "token", alternate = "wechatId")
    private String              wechatId;
    private String              accountId;
    private String              wxid;
    private String              content;
    private WechatMessageType   type;
    private WechatMessageStatus status;
    private Date                conversationTime;
    private Date                createTime;
    private Date                updateTime;
}

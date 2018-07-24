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

    @SerializedName(value = "wechatId", alternate = "token")
    private String              wechatId;
    @SerializedName(value = "wxid", alternate = "username")
    private String              wxid;
    @SerializedName(value = "type", alternate = "msgType")
    private WechatMessageType   type;
    private String              sellerId;
    private String              content;
    private String              extract;
    private WechatMessageStatus status;
    private Date                conversationTime;
    private Date                createTime;
    private Date                updateTime;
}

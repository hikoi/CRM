package com.crm.core.im.entity;

import com.crm.core.wechat.consts.WechatMessageType;
import lombok.Getter;
import lombok.Setter;
import org.wah.doraemon.domain.Createable;
import org.wah.doraemon.domain.Entity;
import org.wah.doraemon.domain.Updateable;

import java.util.Date;

@Getter
@Setter
public class IMCustomMessage extends Entity implements Createable, Updateable{

    //客户微信号
    private String  wxno;
    //发送者
    private String  fromAccount;
    //接收者
    private String  toAccount;
    //消息正文
    private String  content;
    //发送时间
    private Date    conversationTime;
    //消息类型
    private WechatMessageType type;

    private Date   createTime;
    private Date   updateTime;
}

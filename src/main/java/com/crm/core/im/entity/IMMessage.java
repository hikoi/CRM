package com.crm.core.im.entity;

import com.crm.core.im.consts.IMMessageType;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import org.wah.doraemon.domain.Createable;
import org.wah.doraemon.domain.Entity;
import org.wah.doraemon.domain.Updateable;

import java.util.Date;
import java.util.List;

/**
 * @link https://cloud.tencent.com/document/product/269/2282
 */
@Getter
@Setter
public class IMMessage extends Entity implements Createable, Updateable{

    @SerializedName("SyncOtherMachine")
    private Integer syncOtherMachine;

    @SerializedName("From_Account")
    private String fromAccount;

    @SerializedName("To_Account")
    private String toAccount;

    @SerializedName("MsgLifeTime")
    private Integer msgLifeTime;

    @SerializedName("MsgRandom")
    private Long msgRandom;

    @SerializedName("MsgTimeStamp")
    private Long msgTimeStamp;

    @SerializedName("MsgBody")
    private List<IMMsgBody> msgBody;

    @SerializedName("MsgType")
    private IMMessageType type;

    private Date createTime;
    private Date updateTime;
}

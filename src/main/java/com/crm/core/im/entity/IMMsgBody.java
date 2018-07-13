package com.crm.core.im.entity;

import com.crm.core.im.consts.IMMessageType;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class IMMsgBody{

    @SerializedName("MsgType")
    private IMMessageType msgType;

    @SerializedName("MsgContent")
    private Map<String, Object> msgContent;
}

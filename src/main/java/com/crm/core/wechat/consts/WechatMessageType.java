package com.crm.core.wechat.consts;

import com.google.gson.annotations.SerializedName;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum WechatMessageType{

    @SerializedName("0")
    TEXT(0, "文本"),

    @SerializedName("1")
    IMAGE(1, "图片"),

    @SerializedName("2")
    VOICE(2, "语音"),

    @SerializedName("3")
    RED_PACKET(3, "红包"),

    @SerializedName("4")
    EMOTICONS(4, "表情"),

    @SerializedName("5")
    SYSTEM(5, "系统"),

    @SerializedName("6")
    TRANSFER(6, "转账"),

    @SerializedName("7")
    VIDEO(7, "视频"),

    @SerializedName("8")
    NEW_FRIEND(8, "新好友"),

    @SerializedName("9")
    NOT_FRIEND(9, "非好友"),

    @SerializedName("10")
    GET_WXNO(10, "查询微信号"),

    @SerializedName("999")
    UNKNOWN(999, "未知");

    private int id;
    private String description;

    public static WechatMessageType getById(int id){
        for(WechatMessageType type : WechatMessageType.values()){
            if(type.getId() == id){
                return type;
            }
        }

        return UNKNOWN;
    }
}

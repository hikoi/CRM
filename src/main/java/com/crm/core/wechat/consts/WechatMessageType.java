package com.crm.core.wechat.consts;

import com.google.gson.annotations.SerializedName;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum WechatMessageType{

    @SerializedName("1")
    TEXT("1", "文本"),

    @SerializedName("3")
    IMAGE("3", "图片"),

    @SerializedName("34")
    VOICE("34", "语音"),

    @SerializedName("42")
    PERSON_CARD("42", "个人名片"),

    @SerializedName("47")
    EMOTICONS("47", "动画图片"),

    @SerializedName("49")
    SHARE("49", "分享"),

    @SerializedName("50")
    ONLINE_TALK("50", "视频/语音聊天"),

    @SerializedName("10000")
    SYSTEM("10000", "系统消息"),

    @SerializedName(" 436207665")
    LUCK_PACKAGE("436207665", "红包"),

    @SerializedName("419430449")
    TRANSFER("419430449", "转账"),

    @SerializedName("999")
    UNKNOWN("999", "未知");

    private String id;
    private String description;

    public static WechatMessageType getById(String id){
        if(StringUtils.isBlank(id)){
            return UNKNOWN;
        }

        for(WechatMessageType type : WechatMessageType.values()){
            if(type.id.equals(id)){
                return type;
            }
        }

        return UNKNOWN;
    }
}

package com.crm.core.wechat.consts;

import com.google.gson.annotations.SerializedName;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.wah.doraemon.domain.consts.EnumType;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum WechatMessageStatus implements EnumType{

    @SerializedName("1")
    SENDING(1, "正在发送"),

    @SerializedName("2")
    SEND_SUCCESS(2, "已发送成功"),

    @SerializedName("3")
    RECEIVE(3, "接收"),

    @SerializedName("4")
    SYSTEM(4, "系统消息"),

    @SerializedName("6")
    ONLINE_TALK(6, "视频/语音聊天"),

    @SerializedName("999")
    UNKNOWN(999, "未知");

    private int id;
    private String description;

    public static WechatMessageStatus getById(int id){
        for(WechatMessageStatus status : WechatMessageStatus.values()){
            if(status.id == id){
                return status;
            }
        }

        return UNKNOWN;
    }
}

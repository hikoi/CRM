package com.crm.core.wechat.consts;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.wah.doraemon.domain.consts.EnumType;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum WechatMessageStatus implements EnumType{

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

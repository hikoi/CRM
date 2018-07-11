package com.crm.core.im.consts;

import com.google.gson.annotations.SerializedName;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.wah.doraemon.domain.consts.EnumType;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum IMUserType implements EnumType{

    @SerializedName("0")
    SELLER(0, "销售"),

    @SerializedName("1")
    WECHAT(1, "微信"),

    @SerializedName("999")
    UNKNOWN(999, "未知");

    private int    id;
    private String description;

    public static IMUserType getById(int id){
        for(IMUserType type : IMUserType.values()){
            if(type.id == id){
                return type;
            }
        }

        return UNKNOWN;
    }
}


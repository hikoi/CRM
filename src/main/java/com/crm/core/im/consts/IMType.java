package com.crm.core.im.consts;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.wah.doraemon.domain.consts.EnumType;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum IMType implements EnumType{

    SELLER(0, "销售"),
    WECHAT(1, "微信");

    private int    id;
    private String description;

    public static IMType getById(int id){
        for(IMType type : IMType.values()){
            if(type.id == id){
                return type;
            }
        }

        return null;
    }
}


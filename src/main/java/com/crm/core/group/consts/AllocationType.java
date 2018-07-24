package com.crm.core.group.consts;

import com.google.gson.annotations.SerializedName;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.wah.doraemon.domain.consts.EnumType;
import org.wah.doraemon.security.exception.UnknownEnumTypeException;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum AllocationType implements EnumType{

    @SerializedName("0")
    BY_GROUP(0, "按组分配"),

    @SerializedName("1")
    BY_SELLER(1, "按销售分配");

    private int    id;
    private String description;

    public static AllocationType getById(int id){
        for(AllocationType type : AllocationType.values()){
            if(type.id == id){
                return type;
            }
        }

        throw new UnknownEnumTypeException("未知的分配类型ID[{0}]", id);
    }
}

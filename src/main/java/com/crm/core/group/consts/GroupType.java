package com.crm.core.group.consts;

import com.google.gson.annotations.SerializedName;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.wah.doraemon.domain.consts.EnumType;
import org.wah.doraemon.security.exception.UnknownEnumTypeException;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum GroupType implements EnumType{

    @SerializedName("0")
    FRIEND(0, "好友分组"),

    @SerializedName("1")
    WECHAT(1, "微信分组"),

    @SerializedName("2")
    SELLER(2, "销售分组"),

    @SerializedName("3")
    MENU(3, "菜单分组");

    private int id;
    private String description;

    public static GroupType getById(int id){
        for(GroupType type : GroupType.values()){
            if(type.id == id){
                return type;
            }
        }

        throw new UnknownEnumTypeException("未知的分组类型ID[{0}]", id);
    }
}

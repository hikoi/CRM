package com.crm.core.device.consts;

import com.google.gson.annotations.SerializedName;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.wah.doraemon.domain.consts.EnumType;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum DeviceType implements EnumType{

    @SerializedName("0")
    ANDROID_MOBILE(0, "安卓手机"),

    @SerializedName("1")
    IPHONE(1, "苹果手机"),

    @SerializedName("2")
    ANDROID_tablet(2, "平板电脑"),

    @SerializedName("3")
    IPAD(3, "苹果平板"),

    @SerializedName("999")
    UNKNOWN(999, "未知");

    private int id;
    private String description;

    public static DeviceType getById(int id){
        for(DeviceType type : DeviceType.values()){
            if(type.id == id){
                return type;
            }
        }

        return UNKNOWN;
    }
}

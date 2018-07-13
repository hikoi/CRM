package com.crm.core.im.consts;

import com.google.gson.annotations.SerializedName;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.wah.doraemon.domain.consts.EnumType;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum IMMessageType implements EnumType{

    @SerializedName("TIMTextElem")
    TIM_TEXT_ELEM(0, "文本消息"),

    @SerializedName("TIMLocationElem")
    TIM_LOCATION_ELEM(1, "地理位置消息"),

    @SerializedName("TIMFaceElem")
    TIM_FACE_ELEM(2, "表情消息"),

    @SerializedName("TIMCustomElem")
    TIM_CUSTOM_ELEM(3, "自定义消息"),

    @SerializedName("TIMSoundElem")
    TIM_SOUND_ELEM(4, "语音消息"),

    @SerializedName("TIMImageElem")
    TIM_IMAGE_ELEM(5, "图像消息"),

    @SerializedName("TIMFileElem")
    TIM_FILE_ELEM(6, "文件消息"),

    @SerializedName("DeleteFriendElem")
    TIM_DELETE_FRIEND_ELEM(10, "好友移除"),

    @SerializedName("AddFriendElem")
    TIM_ADD_FRIEND_ELEM(11, "好友新增"),

    @SerializedName("Unknown")
    UNKNOWN(999, "未知");

    private int    id;
    private String description;

    public static IMMessageType getById(int id){
        for(IMMessageType type : IMMessageType.values()){
            if(type.id == id){
                return type;
            }
        }

        return UNKNOWN;
    }
}

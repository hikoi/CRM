package com.crm.core.wechat.entity;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.wah.doraemon.domain.Createable;
import org.wah.doraemon.domain.Entity;
import org.wah.doraemon.domain.Updateable;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class Wechat implements Createable, Updateable{

    @SerializedName(value = "token", alternate = "id")
    private String  id;
    private String  companyId;
    private String  deviceId;
    private String  wxno;
    private String  nickname;
    private Date    createTime;
    private Date    updateTime;

    private List<WechatFriend> friends;

    public boolean equals(Object object){
        if(this == object){
            return true;
        }

        if(object != null && this.getClass() == object.getClass()){
            Wechat entity = (Wechat) object;

            if(!StringUtils.isBlank(this.id)){
                return this.id.equals(entity.id);
            }
        }

        return false;
    }

    public int hashCode(){
        return !StringUtils.isBlank(this.id) ? this.id.hashCode() : 0;
    }
}

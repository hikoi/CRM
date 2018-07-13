package com.crm.core.wechat.entity;

import com.crm.core.wechat.consts.WechatFriendType;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.wah.doraemon.domain.Createable;
import org.wah.doraemon.domain.Entity;
import org.wah.doraemon.domain.Updateable;

import java.util.Date;

@Getter
@Setter
public class WechatFriend extends Entity implements Createable, Updateable{

    private String           wechatId;
    private String           sellerId;
    private String           wxid;
    private String           wxno;
    private String           nickname;
    private String           remarkname;
    private String           headImgUrl;
    private WechatFriendType type;
    private Date             createTime;
    private Date             updateTime;

    @Override
    public boolean equals(Object object){
        if(this == object){
            return true;
        }

        if(object != null && this.getClass() == object.getClass()){
            WechatFriend entity = (WechatFriend) object;

            if(StringUtils.isNotBlank(this.getId()) && StringUtils.isNotBlank(entity.getId())){
                return this.getId().equals(entity.getId());

            }else if(StringUtils.isNotBlank(this.wxid) &&
                     StringUtils.isNotBlank(entity.wxid) &&
                     StringUtils.isNotBlank(this.wechatId) &&
                     StringUtils.isNotBlank(entity.wechatId)){

                return this.wxid.equals(entity.wxid) && this.wechatId.equals(entity.wechatId);
            }
        }

        return false;
    }
}

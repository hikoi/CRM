package com.crm.core.im.entity;

import com.crm.core.im.consts.IMUserType;
import lombok.Getter;
import lombok.Setter;
import org.wah.doraemon.domain.Createable;
import org.wah.doraemon.domain.Entity;
import org.wah.doraemon.domain.Updateable;

import java.util.Date;

@Getter
@Setter
public class IMUser extends Entity implements Createable, Updateable{

    private String relationId;
    private String name;
    private String nickname;
    private String headImgUrl;
    private String sig;
    private String sdkAppId;
    private IMUserType type;
    private Date createTime;
    private Date updateTime;
}

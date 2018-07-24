package com.crm.core.wechat.entity;

import com.crm.core.wechat.consts.PurposeType;
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
public class Wechat extends Entity implements Createable, Updateable{

    private String      companyId;
    private String      deviceId;
    private String      wxno;
    private String      nickname;
    private PurposeType type;
    private Date        createTime;
    private Date        updateTime;

    private List<WechatFriend> friends;
}

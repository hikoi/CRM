package com.crm.core.jpush.entity;

import lombok.Getter;
import lombok.Setter;
import org.wah.doraemon.domain.Createable;
import org.wah.doraemon.domain.Entity;
import org.wah.doraemon.domain.Updateable;

import java.util.Date;

@Getter
@Setter
public class JPush extends Entity implements Createable, Updateable{

    private String registrationId;
    private String wxno;
    private Date   createTime;
    private Date   updateTime;
}

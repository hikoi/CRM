package com.crm.core.device.entity;

import com.crm.core.device.consts.DeviceType;
import lombok.Getter;
import lombok.Setter;
import org.wah.doraemon.domain.Createable;
import org.wah.doraemon.domain.Entity;
import org.wah.doraemon.domain.Updateable;

import java.util.Date;

@Getter
@Setter
public class Device extends Entity implements Createable, Updateable{

    private String     phone;
    private String     imei;
    private String     meid;
    private DeviceType type;
    private String     companyId;
    private Date       createTime;
    private Date       updateTime;
}

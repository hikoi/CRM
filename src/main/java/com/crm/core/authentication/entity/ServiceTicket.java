package com.crm.core.authentication.entity;

import lombok.Getter;
import lombok.Setter;
import org.wah.doraemon.domain.Createable;
import org.wah.doraemon.domain.Entity;
import org.wah.doraemon.domain.Updateable;

import java.util.Date;

@Getter
@Setter
public class ServiceTicket extends Entity implements Createable, Updateable{

    private String  accountId;
    private Integer expire;
    private Date    createTime;
    private Date    updateTime;
}

package com.crm.core.account.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestMethod;
import org.wah.doraemon.domain.Createable;
import org.wah.doraemon.domain.Entity;
import org.wah.doraemon.domain.Updateable;

import java.util.Date;

@Getter
@Setter
public class Permission extends Entity implements Createable, Updateable{

    private String        url;
    private String        description;
    private Boolean       needAllot;
    private RequestMethod method;
    private Date          createTime;
    private Date          updateTime;
}

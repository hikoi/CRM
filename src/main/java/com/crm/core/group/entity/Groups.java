package com.crm.core.group.entity;

import com.crm.core.group.consts.GroupType;
import lombok.Getter;
import lombok.Setter;
import org.wah.doraemon.domain.Createable;
import org.wah.doraemon.domain.Entity;
import org.wah.doraemon.domain.Updateable;
import org.wah.doraemon.entity.consts.UsingState;

import java.util.Date;

@Getter
@Setter
public class Groups extends Entity implements Createable, Updateable{

    private String     name;
    private UsingState state;
    private String     companyId;
    private Boolean    isDefault;
    private GroupType  type;
    private Date       createTime;
    private Date       updateTime;
}

package com.crm.core.group.entity;

import com.crm.core.group.consts.AllocationType;
import lombok.Getter;
import lombok.Setter;
import org.wah.doraemon.domain.Createable;
import org.wah.doraemon.domain.Entity;
import org.wah.doraemon.domain.Updateable;
import org.wah.doraemon.entity.consts.UsingState;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class AllocationRule extends Entity implements Createable, Updateable{

    private String         name;
    private List<String>   regions;
    private List<String>   wechatGroups;
    private UsingState     state;
    private AllocationType type;
    private Boolean        onlineOnly;
    private Date           createTime;
    private Date           updateTime;
}

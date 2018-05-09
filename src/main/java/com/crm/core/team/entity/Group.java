package com.crm.core.team.entity;

import com.crm.core.sensitive.entity.Sensitive;
import com.crm.core.wechat.entity.Wechat;
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
public class Group extends Entity implements Createable, Updateable{

    private String          name;
    private List<Wechat>    wechats;
    private List<Sensitive> sensitives;
    private UsingState      state;
    private Date            createTime;
    private Date            updateTime;
}

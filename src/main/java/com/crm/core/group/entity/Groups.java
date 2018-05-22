package com.crm.core.group.entity;

import com.crm.core.sensitive.entity.SensitiveWord;
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
public class Groups extends Entity implements Createable, Updateable{

    private String          name;
    private List<Wechat>    wechats;
    private List<SensitiveWord> sensitiveWords;
    private UsingState      state;
    private Date            createTime;
    private Date            updateTime;
}

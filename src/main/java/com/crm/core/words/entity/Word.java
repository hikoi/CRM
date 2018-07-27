package com.crm.core.words.entity;

import lombok.Getter;
import lombok.Setter;
import org.wah.doraemon.domain.Createable;
import org.wah.doraemon.domain.Entity;
import org.wah.doraemon.domain.Updateable;
import org.wah.doraemon.entity.consts.UsingState;

import java.util.Date;

@Getter
@Setter
public class Word extends Entity implements Createable, Updateable{

    private String     content;
    private UsingState state;
    private Date       createTime;
    private Date       updateTime;
}

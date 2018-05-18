package com.crm.core.account.entity;

import lombok.Getter;
import lombok.Setter;
import org.wah.doraemon.domain.Createable;
import org.wah.doraemon.domain.Entity;
import org.wah.doraemon.domain.Updateable;
import org.wah.doraemon.entity.User;
import org.wah.doraemon.entity.consts.UsingState;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class AccountGroup extends Entity implements Createable, Updateable{

    private String     name;
    private UsingState state;
    private Date       createTime;
    private Date       updateTime;

    private List<User> users;
    private List<Role> roles;
}

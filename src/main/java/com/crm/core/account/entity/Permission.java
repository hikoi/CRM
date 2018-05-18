package com.crm.core.account.entity;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.wah.doraemon.domain.Createable;
import org.wah.doraemon.domain.Entity;
import org.wah.doraemon.domain.Updateable;

import java.util.Date;

@Getter
@Setter
public class Permission extends Entity implements Createable, Updateable{

    private String  url;
    private String  description;
    private Boolean needAllot;
    private Date    createTime;
    private Date    updateTime;

    @Override
    public boolean equals(Object object){
        if(this == object){
            return true;
        }

        if(object != null && this.getClass() == object.getClass()){
            Permission entity = (Permission) object;

            if(StringUtils.isNotBlank(entity.url) && StringUtils.isNotBlank(this.url)){
                return this.url.equals(entity.url);
            }
        }

        return false;
    }
}

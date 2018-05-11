package com.crm.core.team.service;

import com.crm.core.team.entity.Groups;
import org.wah.doraemon.entity.consts.UsingState;
import org.wah.doraemon.security.request.Page;
import org.wah.doraemon.security.request.PageRequest;

public interface GroupsService{

    void save(Groups group);

    void update(Groups group);

    Groups getById(String id);

    Page<Groups> page(PageRequest pageRequest, String id, String name, UsingState state);
}

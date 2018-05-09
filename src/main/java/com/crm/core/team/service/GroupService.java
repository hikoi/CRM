package com.crm.core.team.service;

import com.crm.core.team.entity.Group;
import org.wah.doraemon.entity.consts.UsingState;
import org.wah.doraemon.security.request.Page;
import org.wah.doraemon.security.request.PageRequest;

public interface GroupService{

    void save(Group group);

    void update(Group group);

    Group getById(String id);

    Page<Group> page(PageRequest pageRequest, String id, String name, UsingState state);
}

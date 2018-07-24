package com.crm.core.group.service;

import com.crm.core.group.consts.GroupType;
import com.crm.core.group.entity.Groups;
import org.wah.doraemon.entity.consts.UsingState;
import org.wah.doraemon.security.request.Page;
import org.wah.doraemon.security.request.PageRequest;

import java.util.List;

public interface GroupsService{

    void save(Groups group);

    void update(Groups group);

    Groups getById(String id);

    Page<Groups> page(PageRequest pageRequest, String companyId, String name, GroupType type, UsingState state);
}

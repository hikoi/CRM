package com.crm.core.organization.service;

import com.crm.core.organization.entity.Position;
import org.wah.doraemon.security.request.Page;
import org.wah.doraemon.security.request.PageRequest;

public interface PositionService{

    void save(Position department);

    void update(Position department);

    Position getById(String id);

    Page<Position> page(PageRequest pageRequest, String id, String name, String departmentId);
}

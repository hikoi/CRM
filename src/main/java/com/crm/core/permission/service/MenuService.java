package com.crm.core.permission.service;

import com.crm.core.permission.entity.Menu;
import org.wah.doraemon.security.request.Page;
import org.wah.doraemon.security.request.PageRequest;

public interface MenuService{

    void save(Menu menu);

    void update(Menu menu);

    Menu getById(String id);

    Page<Menu> page(PageRequest pageRequest, String id, String name, String url, String parentId, Boolean isParent);
}

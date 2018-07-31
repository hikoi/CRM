package com.crm.core.permission.service;

import com.crm.core.permission.entity.Menu;
import org.wah.doraemon.security.request.Page;
import org.wah.doraemon.security.request.PageRequest;

import java.util.List;

public interface MenuService{

    void save(String groupsId, Menu menu);

    void saveList(String groupsId, List<Menu> menus);

    void update(Menu menu);

    Menu getById(String id);

    List<Menu> findByAccountId(String accountId);

    List<String> findByTicket(String ticket);
}

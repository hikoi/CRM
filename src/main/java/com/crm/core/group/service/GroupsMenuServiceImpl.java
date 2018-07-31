package com.crm.core.group.service;

import com.crm.core.group.dao.GroupsMenuDao;
import com.crm.core.group.entity.GroupsMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class GroupsMenuServiceImpl implements GroupsMenuService{

    @Autowired
    private GroupsMenuDao groupsMenuDao;

    @Override
    public List<GroupsMenu> find(String groupsId, String menuId){
        return groupsMenuDao.find(groupsId, menuId);
    }
}

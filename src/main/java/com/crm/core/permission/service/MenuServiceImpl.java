package com.crm.core.permission.service;

import com.crm.core.permission.dao.MenuDao;
import com.crm.core.permission.entity.Menu;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.wah.doraemon.security.request.Page;
import org.wah.doraemon.security.request.PageRequest;

@Service
@Transactional(readOnly = true)
public class MenuServiceImpl implements MenuService{

    private MenuDao menuDao;

    @Override
    @Transactional(readOnly = false)
    public void save(Menu menu){
        Assert.notNull(menu, "菜单信息不能为空");

        menuDao.saveOrUpdate(menu);
    }

    @Override
    @Transactional(readOnly = false)
    public void update(Menu menu){
        Assert.notNull(menu, "菜单信息不能为空");
        Assert.hasText(menu.getId(), "菜单ID不能为空");

        menuDao.saveOrUpdate(menu);
    }

    @Override
    public Menu getById(String id){
        Assert.hasText(id, "菜单ID不能为空");

        return menuDao.getById(id);
    }

    @Override
    public Page<Menu> page(PageRequest pageRequest, String id, String name, String url, String parentId, Boolean isParent){
        Assert.notNull(pageRequest, "分页信息不能为空");

        return menuDao.page(pageRequest, id, name, url, parentId, isParent);
    }
}

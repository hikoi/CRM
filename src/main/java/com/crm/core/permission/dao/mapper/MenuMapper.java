package com.crm.core.permission.dao.mapper;

import com.crm.core.permission.entity.Menu;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.wah.doraemon.utils.mybatis.Criteria;

import java.util.List;

@Repository
public interface MenuMapper{

    void save(Menu menu);

    void saveList(@Param("menus") List<Menu> menus);

    void update(Menu menu);

    Menu get(@Param("params") Criteria criteria);

    List<Menu> find(@Param("params") Criteria criteria);

    Long count(@Param("params") Criteria criteria);
}

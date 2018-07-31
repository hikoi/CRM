package com.crm.core.group.dao.mapper;

import com.crm.core.group.entity.GroupsMenu;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.wah.doraemon.utils.mybatis.Criteria;

import java.util.List;

@Repository
public interface GroupsMenuMapper{

    void save(@Param("groupsId") String groupsId, @Param("menuId") String menuId);

    void saveList(@Param("groupsId") String groupsId, @Param("menuIds") List<String> menuIds);

    void delete(@Param("params") Criteria criteria);

    List<GroupsMenu> find(@Param("params") Criteria criteria);
}

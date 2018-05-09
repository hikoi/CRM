package com.crm.core.team.dao.mapper;

import com.crm.core.team.entity.Group;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.wah.doraemon.utils.mybatis.Criteria;

import java.util.List;

@Repository
public interface GroupMapper{

    void save(Group group);

    void update(Group group);

    Group get(@Param("params") Criteria criteria);

    List<Group> find(@Param("params") Criteria criteria);

    Long count(@Param("params") Criteria criteria);
}
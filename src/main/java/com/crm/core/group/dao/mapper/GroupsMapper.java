package com.crm.core.group.dao.mapper;

import com.crm.core.group.entity.Groups;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.wah.doraemon.utils.mybatis.Criteria;

import java.util.List;

@Repository
public interface GroupsMapper{

    void save(Groups group);

    void update(Groups group);

    Groups get(@Param("params") Criteria criteria);

    List<Groups> find(@Param("params") Criteria criteria);

    Long count(@Param("params") Criteria criteria);
}
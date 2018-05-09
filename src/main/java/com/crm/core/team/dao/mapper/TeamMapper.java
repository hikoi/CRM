package com.crm.core.team.dao.mapper;

import com.crm.core.team.entity.Team;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.wah.doraemon.utils.mybatis.Criteria;

import java.util.List;

@Repository
public interface TeamMapper{

    void save(Team team);

    void update(Team team);

    Team get(@Param("params") Criteria criteria);

    List<Team> find(@Param("params") Criteria criteria);

    Long count(@Param("params") Criteria criteria);
}

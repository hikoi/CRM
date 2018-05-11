package com.crm.core.team.dao.mapper;

import com.crm.core.team.entity.Groups;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.wah.doraemon.utils.mybatis.Criteria;

import java.util.List;

@Repository
public interface GroupsMapper{

    void save(Groups group);

    void update(Groups group);

    void dissolve(@Param("params") Criteria criteria);

    void establishByGroupId(@Param("groupId") String groupId, @Param("wechatIds") List<String> wechatIds);

    void establishByWechatId(@Param("wechatId") String wechatId, @Param("groupIds") List<String> groupIds);

    Groups get(@Param("params") Criteria criteria);

    List<Groups> find(@Param("params") Criteria criteria);

    Long count(@Param("params") Criteria criteria);
}
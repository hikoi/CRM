package com.crm.core.group.dao.mapper;

import com.crm.core.group.entity.GroupsWechat;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.wah.doraemon.utils.mybatis.Criteria;

import java.util.List;

@Repository
public interface GroupsWechatMapper{

    void save(@Param("groupsId") String groupsId, @Param("wechatId") String wechatId);

    void saveList(@Param("groupsId") String groupsId, @Param("wechatIds") List<String> wechatIds);

    void delete(@Param("params") Criteria criteria);

    GroupsWechat get(@Param("params") Criteria criteria);

    List<GroupsWechat> find(@Param("params") Criteria criteria);
}

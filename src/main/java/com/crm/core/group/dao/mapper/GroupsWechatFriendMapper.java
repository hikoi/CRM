package com.crm.core.group.dao.mapper;

import com.crm.core.group.entity.GroupsWechatFriend;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.wah.doraemon.utils.mybatis.Criteria;

import java.util.List;

@Repository
public interface GroupsWechatFriendMapper{

    void save(@Param("groupsId") String groupsId, @Param("friendId") String friendId);

    void saveList(@Param("groupsId") String groupsId, @Param("friendIds") List<String> friendIds);

    void delete(@Param("params") Criteria criteria);

    GroupsWechatFriend get(@Param("params") Criteria criteria);

    List<GroupsWechatFriend> find(@Param("params") Criteria criteria);
}

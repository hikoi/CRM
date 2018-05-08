package com.crm.core.wechat.dao.mapper;

import com.crm.core.wechat.entity.WechatFriend;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.wah.doraemon.utils.mybatis.Criteria;

import java.util.List;

@Repository
public interface WechatFriendMapper{

    void save(WechatFriend friend);

    void saveList(@Param("friends") List<WechatFriend> friends);

    void update(WechatFriend friend);

    void updateList(@Param("friends") List<WechatFriend> friends);

    WechatFriend get(@Param("params") Criteria criteria);

    List<WechatFriend> find(@Param("params") Criteria criteria);

    Long count(@Param("params") Criteria criteria);
}

package com.crm.core.account.dao.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.wah.doraemon.entity.User;
import org.wah.doraemon.utils.mybatis.Criteria;

import java.util.List;

@Repository
public interface UserMapper{

    void save(User user);

    void update(User user);

    User get(@Param("params") Criteria criteria);

    List<User> find(@Param("params") Criteria criteria);

    Long count(@Param("params") Criteria criteria);
}

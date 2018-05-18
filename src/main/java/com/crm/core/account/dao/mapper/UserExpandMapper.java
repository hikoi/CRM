package com.crm.core.account.dao.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.wah.doraemon.utils.mybatis.Criteria;

import java.util.List;

@Repository
public interface UserExpandMapper{

    void save(UserExpandMapper expand);

    void update(UserExpandMapper expand);

    UserExpandMapper get(@Param("params") Criteria criteria);

    List<UserExpandMapper> find(@Param("params") Criteria criteria);
}

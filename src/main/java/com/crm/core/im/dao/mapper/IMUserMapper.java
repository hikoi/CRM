package com.crm.core.im.dao.mapper;

import com.crm.core.im.entity.IMUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.wah.doraemon.utils.mybatis.Criteria;

import java.util.List;

@Repository
public interface IMUserMapper{

    void save(IMUser user);

    void update(IMUser user);

    IMUser get(@Param("params") Criteria criteria);

    List<IMUser> find(@Param("params") Criteria criteria);

    Long count(@Param("params") Criteria criteria);
}

package com.crm.core.jpush.dao.mapper;

import com.crm.core.jpush.entity.JPush;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.wah.doraemon.utils.mybatis.Criteria;

import java.util.List;

@Repository
public interface JPushMapper{

    void save(JPush jPush);

    void update(JPush jPush);

    JPush get(@Param("params") Criteria criteria);

    List<JPush> find(@Param("params") Criteria criteria);
}

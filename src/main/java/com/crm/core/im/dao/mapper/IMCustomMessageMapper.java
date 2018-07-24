package com.crm.core.im.dao.mapper;

import com.crm.core.im.entity.IMCustomMessage;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.wah.doraemon.utils.mybatis.Criteria;

import java.util.List;

@Repository
public interface IMCustomMessageMapper{

    void save(IMCustomMessage message);

    void update(IMCustomMessage message);

    IMCustomMessage get(@Param("params") Criteria criteria);

    List<IMCustomMessage> find(@Param("params") Criteria criteria);

    Long count(@Param("params") Criteria criteria);
}

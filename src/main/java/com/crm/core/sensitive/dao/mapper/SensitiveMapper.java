package com.crm.core.sensitive.dao.mapper;

import com.crm.core.sensitive.entity.Sensitive;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.wah.doraemon.utils.mybatis.Criteria;

import java.util.List;

@Repository
public interface SensitiveMapper{

    void save(Sensitive sensitive);

    void update(Sensitive sensitive);

    Sensitive get(@Param("params") Criteria criteria);

    List<Sensitive> find(@Param("params") Criteria criteria);

    Long count(@Param("params") Criteria criteria);
}

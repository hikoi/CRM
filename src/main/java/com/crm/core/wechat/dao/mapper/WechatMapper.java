package com.crm.core.wechat.dao.mapper;

import com.crm.core.wechat.entity.Wechat;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.wah.doraemon.utils.mybatis.Criteria;

import java.util.List;

@Repository
public interface WechatMapper{

    void save(Wechat wechat);

    void update(Wechat wechat);

    Wechat get(@Param("params") Criteria criteria);

    List<Wechat> find(@Param("params") Criteria criteria);

    Long count(@Param("params") Criteria criteria);
}

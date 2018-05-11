package com.crm.core.sensitive.dao.mapper;

import com.crm.core.sensitive.entity.SensitiveWord;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.wah.doraemon.utils.mybatis.Criteria;

import java.util.List;

@Repository
public interface SensitiveWordMapper{

    void save(SensitiveWord sensitiveWord);

    void update(SensitiveWord sensitiveWord);

    void dissolveByGroupId(String groupId);

    void dissolveByWechatId(String wechatId);

    void establishByGroupId(@Param("groupId") String groupId, @Param("sensitiveIds") List<String> sensitiveIds);

    void establishByWechatId(@Param("wechatId") String wechatId, @Param("sensitiveIds") List<String> sensitiveIds);

    SensitiveWord get(@Param("params") Criteria criteria);

    List<SensitiveWord> find(@Param("params") Criteria criteria);

    Long count(@Param("params") Criteria criteria);
}

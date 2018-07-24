package com.crm.core.group.dao.mapper;

import com.crm.core.group.entity.AllocationRule;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.wah.doraemon.utils.mybatis.Criteria;

import java.util.List;

@Repository
public interface AllocationRuleMapper{

    void save(AllocationRule rule);

    void saveRelations(AllocationRule rule);

    void update(AllocationRule rule);

    void delete(@Param("params") Criteria criteria);

    void deleteRelations(@Param("params") Criteria criteria);

    AllocationRule get(@Param("params") Criteria criteria);

    List<AllocationRule> find(@Param("params") Criteria criteria);
}

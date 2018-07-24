package com.crm.core.group.service;

import com.crm.core.group.consts.AllocationType;
import com.crm.core.group.entity.AllocationRule;
import org.wah.doraemon.entity.consts.UsingState;

import java.util.List;

public interface AllocationRuleService{

    void save(AllocationRule rule);

    void update(AllocationRule rule);

    List<AllocationRule> find(UsingState state, AllocationType type, String wechatGroupsId);
}

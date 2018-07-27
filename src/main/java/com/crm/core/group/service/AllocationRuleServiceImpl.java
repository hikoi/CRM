package com.crm.core.group.service;

import com.crm.core.group.consts.AllocationType;
import com.crm.core.group.dao.AllocationRuleDao;
import com.crm.core.group.entity.AllocationRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.wah.doraemon.entity.consts.UsingState;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class AllocationRuleServiceImpl implements AllocationRuleService{

    @Autowired
    private AllocationRuleDao allocationRuleDao;

    @Override
    @Transactional
    public void save(AllocationRule rule){
        Assert.notNull(rule, "分配规则信息不能为空");
        Assert.notNull(rule.getState(), "分配规则状态不能为空");
        Assert.notNull(rule.getType(), "分配规则类型不能为空");
        Assert.notNull(rule.getOnlineOnly(), "分配规则分配销售状态不能为空");
        Assert.notEmpty(rule.getWechatGroups(), "分配的微信分组ID不能为空");
        Assert.notEmpty(rule.getRegions(), "分配的范围ID不能为空");

        //保存规则
        allocationRuleDao.saveOrUpdate(rule);
        //保存关系
        allocationRuleDao.saveRelations(rule);
    }

    @Override
    @Transactional
    public void update(AllocationRule rule){
        Assert.notNull(rule, "分配规则信息不能为空");
        Assert.hasText(rule.getId(), "分配规则ID不能为空");
        Assert.notNull(rule.getState(), "分配规则状态不能为空");
        Assert.notNull(rule.getType(), "分配规则类型不能为空");
        Assert.notNull(rule.getOnlineOnly(), "分配规则分配销售状态不能为空");
        Assert.notEmpty(rule.getWechatGroups(), "分配的微信分组ID不能为空");
        Assert.notEmpty(rule.getRegions(), "分配的范围ID不能为空");

        //更新规则
        allocationRuleDao.saveOrUpdate(rule);
        //删除关系
        allocationRuleDao.deleteRelationsById(rule.getId());
        //保存关系
        allocationRuleDao.saveRelations(rule);
    }

    @Override
    public List<AllocationRule> find(UsingState state, AllocationType type, List<String> wechatGroupsIds){
        return allocationRuleDao.find(state, type, wechatGroupsIds);
    }
}

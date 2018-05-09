package com.crm.core.sensitive.service;

import com.crm.core.sensitive.dao.SensitiveDao;
import com.crm.core.sensitive.entity.Sensitive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.wah.doraemon.entity.consts.UsingState;
import org.wah.doraemon.security.request.Page;
import org.wah.doraemon.security.request.PageRequest;

@Service
@Transactional(readOnly = true)
public class SensitiveServiceImpl implements SensitiveService{

    @Autowired
    private SensitiveDao sensitiveDao;

    @Override
    @Transactional(readOnly = false)
    public void save(Sensitive sensitive){
        Assert.notNull(sensitive, "敏感词信息不能为空");

        sensitiveDao.saveOrUpdate(sensitive);
    }

    @Override
    @Transactional(readOnly = false)
    public void update(Sensitive sensitive){
        Assert.notNull(sensitive, "敏感词信息不能为空");
        Assert.hasText(sensitive.getId(), "敏感词信息不能为空");

        sensitiveDao.saveOrUpdate(sensitive);
    }

    @Override
    public Sensitive getById(String id){
        Assert.hasText(id, "敏感词ID不能为空");

        return sensitiveDao.getById(id);
    }

    @Override
    public Page<Sensitive> page(PageRequest pageRequest, String content, UsingState state){
        Assert.notNull(pageRequest, "分页信息不能为空");

        return sensitiveDao.page(pageRequest, content, state);
    }
}

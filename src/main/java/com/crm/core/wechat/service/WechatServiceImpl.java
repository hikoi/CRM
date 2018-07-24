package com.crm.core.wechat.service;

import com.crm.core.wechat.dao.WechatDao;
import com.crm.core.wechat.entity.Wechat;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.wah.doraemon.security.exception.DuplicateException;

@Service
@Transactional(readOnly = true)
public class WechatServiceImpl implements WechatService{

    @Autowired
    private WechatDao wechatDao;

    @Override
    @Transactional(readOnly = false)
    public void save(Wechat wechat){
        Assert.notNull(wechat, "微信信息不能为空");
        Assert.hasText(wechat.getCompanyId(), "公司ID不能为空");
        Assert.hasText(wechat.getWxno(), "微信号不能为空");

        if(wechatDao.exist(wechat.getWxno())){
            throw new DuplicateException("微信号[{0}]已注册", wechat.getWxno());
        }

        wechatDao.saveOrUpdate(wechat);
    }

    @Override
    @Transactional(readOnly = false)
    public void update(Wechat wechat){
        Assert.notNull(wechat, "微信信息不能为空");
        Assert.hasText(wechat.getId(), "微信ID不能为空");

        if(StringUtils.isNotBlank(wechat.getWxno()) && wechatDao.exist(wechat.getWxno())){
            throw new DuplicateException("微信号[{0}]已注册", wechat.getWxno());
        }

        wechatDao.saveOrUpdate(wechat);
    }
}

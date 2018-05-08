package com.crm.core.wechat.service;

import com.crm.commons.security.exception.DuplicateException;
import com.crm.core.wechat.dao.WechatDao;
import com.crm.core.wechat.entity.Wechat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.wah.doraemon.security.request.Page;
import org.wah.doraemon.security.request.PageRequest;

@Service
@Transactional(readOnly = true)
public class WechatServiceImpl implements WechatService{

    @Autowired
    private WechatDao wechatDao;

    @Override
    @Transactional(readOnly = false)
    public void save(Wechat wechat){
        Assert.notNull(wechat, "微信信息不能为空");
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
        Assert.hasText(wechat.getWxno(), "微信号不能为空");

        if(wechatDao.exist(wechat.getWxno())){
            throw new DuplicateException("微信号[{0}]已注册", wechat.getWxno());
        }

        wechatDao.saveOrUpdate(wechat);
    }

    @Override
    public Wechat getByWxno(String wxno){
        Assert.hasText(wxno, "微信号不能为空");

        return wechatDao.getByWxno(wxno);
    }

    @Override
    public Page<Wechat> page(PageRequest pageRequest, String accountId, String wxno, String nickname){
        Assert.notNull(pageRequest, "分页信息不能为空");

        return wechatDao.page(pageRequest, accountId, wxno, nickname);
    }
}

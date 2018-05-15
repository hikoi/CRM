package com.crm.core.jpush.service;

import com.crm.core.jpush.dao.JPushDao;
import com.crm.core.jpush.entity.JPush;
import com.crm.core.sensitive.dao.SensitiveWordDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
@Transactional(readOnly = true)
public class JPushServiceImpl implements JPushService{

    @Autowired
    private JPushDao jPushDao;

    @Autowired
    private SensitiveWordDao sensitiveWordDao;

    @Override
    @Transactional(readOnly = false)
    public void save(String wxno, String registrationId){
        Assert.hasText(registrationId, "极光推送账号ID不能为空");
        Assert.hasText(wxno, "极光推送账号微信号不能为空");

        JPush jPush = jPushDao.getByRegistrationId(registrationId);
        if(jPush == null){
            jPush = new JPush();
            jPush.setRegistrationId(registrationId);
        }
        jPush.setWxno(wxno);

        jPushDao.saveOrUpdate(jPush);
    }
}

package com.crm.core.call.service;


import com.crm.core.call.dao.CallRecordDao;
import com.crm.core.call.entity.CallRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.wah.doraemon.entity.consts.Sex;
import org.wah.doraemon.security.request.Page;
import org.wah.doraemon.security.request.PageRequest;

@Service
@Transactional(readOnly = true)
public class CallRecordServiceImpl implements CallRecordService {

    @Autowired
    private CallRecordDao callRecordDao;

    @Override
    @Transactional(readOnly = false)
    public void save(CallRecord callRecord){
        Assert.notNull(callRecord, "用户信息不能为空");
        Assert.hasText(callRecord.getCallId(), "CallId不能为空");

        callRecordDao.save(callRecord);
    }

    @Override
    @Transactional(readOnly = false)
    public void update(CallRecord callRecord){
        Assert.notNull(callRecord, "用户信息不能为空");
        Assert.hasText(callRecord.getCallId(), "CallId不能为空");

        callRecordDao.update(callRecord);
    }

    @Override
    public CallRecord getByCallId(String callId) {
        return callRecordDao.getByCallId(callId);
    }


    @Override
    public Page<CallRecord> page(PageRequest pageRequest, String callerId, String caller){
        Assert.notNull(pageRequest, "分页信息不能为空");

        return callRecordDao.page(pageRequest, callerId, caller);
    }
}

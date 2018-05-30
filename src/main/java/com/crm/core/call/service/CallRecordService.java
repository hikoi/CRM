package com.crm.core.call.service;


import com.crm.core.call.entity.CallRecord;
import org.wah.doraemon.entity.consts.Sex;
import org.wah.doraemon.security.request.Page;
import org.wah.doraemon.security.request.PageRequest;

public interface CallRecordService {

    void save(CallRecord callRecord);

    void update(CallRecord callRecord);

    CallRecord getByCallId(String callId);

    Page<CallRecord> page(PageRequest pageRequest, String callerId, String caller);
}

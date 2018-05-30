package com.crm.core.call.dao;

import com.crm.core.call.dao.mapper.CallRecordMapper;
import com.crm.core.call.entity.CallRecord;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import org.wah.doraemon.entity.consts.Sex;
import org.wah.doraemon.security.exception.DataAccessException;
import org.wah.doraemon.security.request.Page;
import org.wah.doraemon.security.request.PageRequest;
import org.wah.doraemon.utils.mybatis.Criteria;
import org.wah.doraemon.utils.mybatis.Restrictions;

import java.util.Date;
import java.util.List;

@Repository
public class CallRecordDao {

    private Logger logger = LoggerFactory.getLogger(CallRecordDao.class);

    @Autowired
    private CallRecordMapper mapper;

    public void save(CallRecord callRecord) {
        try {
            Assert.notNull(callRecord, "用户信息不能为空");
            callRecord.setStartTime(new Date());
            mapper.save(callRecord);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public void update(CallRecord callRecord) {
        try {
            Assert.notNull(callRecord, "用户信息不能为空");
            mapper.update(callRecord);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }


    public CallRecord getByCallId(String callId) {
        try {
            Assert.hasText(callId, "callId不能为空");
            Criteria criteria = new Criteria();
            criteria.and(Restrictions.eq("callId", callId));
            return mapper.get(criteria);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public Page<CallRecord> page(PageRequest pageRequest, String callerId, String caller) {
        try {
            Assert.notNull(pageRequest, "分页信息不能为空");
            Criteria criteria = new Criteria();
            criteria.limit(Restrictions.limit(pageRequest.getOffset(), pageRequest.getPageSize()));
            criteria.sort(Restrictions.desc("startTime"));
            if (StringUtils.isNotBlank(callerId)) {
                criteria.and(Restrictions.eq("callerId", callerId));
            }
            if (StringUtils.isNotBlank(caller)) {
                criteria.and(Restrictions.eq("caller", caller));
            }
            List<CallRecord> list = mapper.find(criteria);
            Long total = mapper.count(criteria);
            return new Page<CallRecord>(list, total, pageRequest);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }
}

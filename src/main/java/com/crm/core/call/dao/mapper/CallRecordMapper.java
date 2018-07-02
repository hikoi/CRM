package com.crm.core.call.dao.mapper;

import com.crm.core.call.entity.CallRecord;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.wah.doraemon.utils.mybatis.Criteria;

import java.util.List;

@Repository
public interface CallRecordMapper {

    void save(CallRecord callRecord);

    void update(CallRecord callRecord);

    CallRecord get(@Param("params") Criteria criteria);

    List<CallRecord> find(@Param("params") Criteria criteria);

    Long count(@Param("params") Criteria criteria);

    List<CallRecord> findLastestByCalledId(@Param("params") Criteria criteria);

    List<CallRecord> findByCalledAndCaller(@Param("params") Criteria criteria);


}

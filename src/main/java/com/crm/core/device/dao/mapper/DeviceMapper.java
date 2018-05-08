package com.crm.core.device.dao.mapper;

import com.crm.core.device.entity.Device;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.wah.doraemon.utils.mybatis.Criteria;

import java.util.List;

@Repository
public interface DeviceMapper{

    void save(Device device);

    void update(Device device);

    Device get(@Param("params") Criteria criteria);

    List<Device> find(@Param("params") Criteria criteria);

    Long count(@Param("params") Criteria criteria);
}

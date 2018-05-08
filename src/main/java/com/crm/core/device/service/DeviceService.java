package com.crm.core.device.service;

import com.crm.core.device.consts.DeviceType;
import com.crm.core.device.entity.Device;
import org.wah.doraemon.security.request.Page;
import org.wah.doraemon.security.request.PageRequest;

public interface DeviceService{

    void save(Device device);

    void update(Device device);

    Device getById(String id);

    Page<Device> page(PageRequest pageRequest, String phone, String imei, String meid, DeviceType type);
}

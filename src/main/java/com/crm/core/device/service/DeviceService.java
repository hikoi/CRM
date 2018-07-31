package com.crm.core.device.service;

import com.crm.core.device.entity.Device;

public interface DeviceService{

    void save(Device device);

    void update(Device device);

    Device getById(String id);
}

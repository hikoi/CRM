package com.crm.core.device.webservice;

import com.crm.core.device.consts.DeviceType;
import com.crm.core.device.entity.Device;
import com.crm.core.device.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.wah.doraemon.security.request.Page;
import org.wah.doraemon.security.request.PageRequest;
import org.wah.doraemon.security.response.Responsed;

@RestController
@RequestMapping("/api/1.0/device")
public class DeviceRestController{

    @Autowired
    private DeviceService deviceService;

    @RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Responsed<Device> save(@RequestBody Device device){
        deviceService.save(device);

        return new Responsed<Device>("保存成功", device);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Responsed<Device> update(@RequestBody Device device){
        deviceService.update(device);

        return new Responsed<Device>("更新成功", device);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Responsed<Device> getById(@PathVariable("id") String id){
        Device device = deviceService.getById(id);

        return new Responsed<Device>("查询成功", device);
    }

    @RequestMapping(value = "/page", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Responsed<Page<Device>> page(Long pageNum, Long pageSize, String phone, String imei, String meid, DeviceType type){
        PageRequest pageRequest = new PageRequest(pageNum, pageSize);
        Page<Device> page = deviceService.page(pageRequest, phone, imei, meid, type);

        return new Responsed<Page<Device>>("查询成功", page);
    }
}

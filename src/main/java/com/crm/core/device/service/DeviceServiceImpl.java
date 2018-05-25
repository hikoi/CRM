package com.crm.core.device.service;

import com.crm.core.device.consts.DeviceType;
import com.crm.core.device.dao.DeviceDao;
import com.crm.core.device.entity.Device;
import com.crm.core.permission.consts.ResourceType;
import com.crm.core.permission.dao.PermissionDao;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.wah.doraemon.security.request.Page;
import org.wah.doraemon.security.request.PageRequest;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class DeviceServiceImpl implements DeviceService{

    @Autowired
    private DeviceDao deviceDao;

    @Autowired
    private PermissionDao permissionDao;

    @Override
    @Transactional(readOnly = false)
    public void save(Device device){
        Assert.notNull(device, "设备信息不能为空");
        Assert.hasText(device.getCompanyId(), "公司ID不能为空");

        deviceDao.saveOrUpdate(device);
    }

    @Override
    @Transactional(readOnly = false)
    public void update(Device device){
        Assert.notNull(device, "设备信息不能为空");
        Assert.hasText(device.getId(), "设备ID不能为空");

        deviceDao.saveOrUpdate(device);
    }

    @Override
    public Device getById(String id){
        Assert.hasText(id, "设备ID不能为空");

        return deviceDao.getById(id);
    }

    @Override
    public Page<Device> page(PageRequest pageRequest, String companyId, String phone, String imei, String meid, DeviceType type, String accountId){
        Assert.notNull(pageRequest, "分页信息不能为空");

        //设备ID列表
        List<String> ids = new ArrayList<String>();

        //查询资源
        if(StringUtils.isNotBlank(accountId)){
            ids.addAll(permissionDao.findResourceIdsByAccountId(accountId, ResourceType.DEVICE));
        }

        return deviceDao.page(pageRequest, companyId, phone, imei, meid, type, ids);
    }
}

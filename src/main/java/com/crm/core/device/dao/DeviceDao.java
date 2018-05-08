package com.crm.core.device.dao;

import com.crm.core.device.consts.DeviceType;
import com.crm.core.device.dao.mapper.DeviceMapper;
import com.crm.core.device.entity.Device;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import org.wah.doraemon.security.exception.DataAccessException;
import org.wah.doraemon.security.request.Page;
import org.wah.doraemon.security.request.PageRequest;
import org.wah.doraemon.utils.IDGenerator;
import org.wah.doraemon.utils.mybatis.Criteria;
import org.wah.doraemon.utils.mybatis.Restrictions;

import java.util.Date;
import java.util.List;

@Repository
public class DeviceDao{

    private Logger logger = LoggerFactory.getLogger(DeviceDao.class);

    @Autowired
    private DeviceMapper mapper;

    public void saveOrUpdate(Device device){
        try{
            Assert.notNull(device, "设备信息不能为空");

            if(StringUtils.isBlank(device.getId())){
                device.setId(IDGenerator.uuid32());
                device.setCreateTime(new Date());
                mapper.save(device);
            }else{
                device.setUpdateTime(new Date());
                mapper.update(device);
            }
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public Device getById(String id){
        try{
            Assert.hasText(id, "设备ID不能为空");

            Criteria criteria = new Criteria();
            criteria.and(Restrictions.eq("id", id));

            return mapper.get(criteria);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public Page<Device> page(PageRequest pageRequest, String phone, String imei, String meid, DeviceType type){
        try{
            Assert.notNull(pageRequest, "分页信息不能为空");

            Criteria criteria = new Criteria();
            criteria.limit(Restrictions.limit(pageRequest.getOffset(), pageRequest.getPageSize()));
            criteria.sort(Restrictions.desc("createTime"));

            if(StringUtils.isNotBlank(phone)){
                criteria.and(Restrictions.like("phone", phone));
            }
            if(StringUtils.isNotBlank(imei)){
                criteria.and(Restrictions.like("imei", imei));
            }
            if(StringUtils.isNotBlank(meid)){
                criteria.and(Restrictions.like("meid", meid));
            }
            if(type != null){
                criteria.and(Restrictions.eq("type", type.getId()));
            }

            List<Device> list = mapper.find(criteria);
            Long total = mapper.count(criteria);

            return new Page<Device>(list, total, pageRequest);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }
}

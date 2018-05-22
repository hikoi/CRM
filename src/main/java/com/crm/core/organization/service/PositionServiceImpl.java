package com.crm.core.organization.service;

import com.crm.core.organization.dao.PositionDao;
import com.crm.core.organization.entity.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.wah.doraemon.security.request.Page;
import org.wah.doraemon.security.request.PageRequest;

@Service
@Transactional(readOnly = true)
public class PositionServiceImpl implements PositionService{

    @Autowired
    private PositionDao positionDao;

    @Override
    @Transactional(readOnly = false)
    public void save(Position position){
        Assert.notNull(position, "岗位信息不能为空");
        Assert.hasText(position.getDepartmentId(), "部门ID不能为空");

        positionDao.saveOrUpdate(position);
    }

    @Override
    @Transactional(readOnly = false)
    public void update(Position position){
        Assert.notNull(position, "岗位信息不能为空");
        Assert.hasText(position.getId(), "岗位ID不能为空");

        positionDao.saveOrUpdate(position);
    }

    @Override
    public Position getById(String id){
        Assert.hasText(id, "岗位ID不能为空");

        return positionDao.getById(id);
    }

    @Override
    public Page<Position> page(PageRequest pageRequest, String id, String name, String departmentId){
        Assert.notNull(pageRequest, "分页信息不能为空");

        return positionDao.page(pageRequest, id, name, departmentId);
    }
}

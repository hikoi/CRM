package com.crm.core.permission.service;

import com.crm.core.permission.consts.ResourceType;
import com.crm.core.permission.dao.FunctionDao;
import com.crm.core.permission.dao.PermissionDao;
import com.crm.core.permission.entity.Function;
import com.crm.core.permission.entity.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class FunctionServiceImpl implements FunctionService{

    @Autowired
    private FunctionDao functionDao;

    @Autowired
    private PermissionDao permissionDao;

    @Override
    @Transactional
    public void save(Function function){
        Assert.notNull(function, "功能信息不能为空");

        functionDao.saveOrUpdate(function);
    }

    @Override
    @Transactional
    public void update(Function function){
        Assert.notNull(function, "功能信息不能为空");
        Assert.hasText(function.getId(), "功能ID不能为空");

        functionDao.saveOrUpdate(function);
    }

    @Override
    @Transactional
    public void saveList(List<Function> functions){
        Assert.notEmpty(functions, "功能信息列表不能为空");

        //原有功能
        List<Function> original = functionDao.find(null, null, null, null, null);
        //新增功能
        List<Function> saveList = new ArrayList<Function>();
        //更新功能
        List<Function> updateList = new ArrayList<Function>();
        //新增权限
        List<Permission> permissions = new ArrayList<Permission>();

        for(Function function : functions){
            Assert.notNull(function, "功能信息不能为空");
            Assert.hasText(function.getUrl(), "功能路径不能为空");
            Assert.notNull(function.getMethod(), "功能请求类型不能为空");

            //已有功能
            if(original.contains(function)){
                Function origin = original.get(original.indexOf(function));
                function.setId(origin.getId());

                updateList.add(function);
            }else{
                saveList.add(function);
            }
        }

        if(saveList != null && !saveList.isEmpty()){
            functionDao.saveList(saveList);

            for(Function function : saveList){
                Permission permission = new Permission();
                permission.setType(ResourceType.FUNCTION);
                permission.setResourceId(function.getId());

                permissions.add(permission);
            }
            permissionDao.saveList(permissions);
        }
        if(updateList != null && !updateList.isEmpty()){
            functionDao.updateList(updateList);
        }
    }

    @Override
    public Function getById(String id){
        Assert.hasText(id, "功能ID不能为空");

        return functionDao.getById(id);
    }

    @Override
    public List<Function> find(String url, String description, Boolean needAllot, RequestMethod method){
        return functionDao.find(url, description, method, needAllot, null);
    }
}

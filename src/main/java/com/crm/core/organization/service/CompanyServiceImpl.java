package com.crm.core.organization.service;

import com.crm.core.organization.dao.CompanyDao;
import com.crm.core.organization.entity.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.wah.doraemon.security.request.Page;
import org.wah.doraemon.security.request.PageRequest;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class CompanyServiceImpl implements CompanyService{

    @Autowired
    private CompanyDao companyDao;

    @Override
    @Transactional(readOnly = false)
    public void save(Company company){
        Assert.notNull(company, "公司信息不能为空");

        companyDao.saveOrUpdate(company);
    }

    @Override
    @Transactional(readOnly = false)
    public void update(Company company){
        Assert.notNull(company, "公司信息不能为空");
        Assert.hasText(company.getId(), "公司ID不能为空");

        companyDao.saveOrUpdate(company);
    }

    @Override
    public Company getById(String id){
        Assert.hasText(id, "公司ID不能为空");

        return companyDao.getById(id);
    }

    @Override
    public List<Company> find(String id, String name, String address, String phone){
        return companyDao.find(id, name, address, phone);
    }

    @Override
    public Page<Company> page(PageRequest pageRequest, String id, String name, String address, String phone){
        Assert.notNull(pageRequest, "分页信息不能为空");

        return companyDao.page(pageRequest, id, name, address, phone);
    }
}

package com.crm.core.organization.service;

import com.crm.core.organization.entity.Company;
import org.wah.doraemon.security.request.Page;
import org.wah.doraemon.security.request.PageRequest;

import java.util.List;

public interface CompanyService{

    void save(Company company);

    void update(Company company);

    Company getById(String id);
}

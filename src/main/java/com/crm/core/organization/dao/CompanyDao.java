package com.crm.core.organization.dao;

import com.crm.core.organization.dao.mapper.CompanyMapper;
import com.crm.core.organization.entity.Company;
import com.crm.core.permission.consts.ResourceType;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Repository
public class CompanyDao{

    private Logger logger = LoggerFactory.getLogger(CompanyDao.class);

    @Autowired
    private CompanyMapper mapper;

    public void saveOrUpdate(Company company){
        try{
            Assert.notNull(company, "公司信息不能为空");

            if(StringUtils.isBlank(company.getId())){
                company.setId(IDGenerator.uuid32());
                company.setCreateTime(new Date());
                mapper.save(company);
            }else{
                company.setUpdateTime(new Date());
                mapper.update(company);
            }
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public Company getById(String id){
        try{
            Assert.hasText(id, "公司ID不能为空");

            Criteria criteria = new Criteria();
            criteria.and(Restrictions.eq("id", id));

            return mapper.get(criteria);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public List<Company> find(String id, String name, String address, String phone, List<String> ids){
        try{
            Criteria criteria = new Criteria();
            criteria.sort(Restrictions.desc("createTime"));

            if(StringUtils.isNotBlank(id)){
                criteria.and(Restrictions.eq("id", id));
            }
            if(StringUtils.isNotBlank(name)){
                criteria.and(Restrictions.like("name", name));
            }
            if(StringUtils.isNotBlank(address)){
                criteria.and(Restrictions.like("address", address));
            }
            if(StringUtils.isNotBlank(phone)){
                criteria.and(Restrictions.like("phone", phone));
            }
            if(ids != null && !ids.isEmpty()){
                criteria.and(Restrictions.in("id", ids));
            }

            return mapper.find(criteria);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public Page<Company> page(PageRequest pageRequest, String name, String address, String phone, List<String> ids){
        try{
            Assert.notNull(pageRequest, "分页信息不能为空");

            Criteria criteria = new Criteria();
            criteria.limit(Restrictions.limit(pageRequest.getOffset(), pageRequest.getPageSize()));
            criteria.sort(Restrictions.desc("createTime"));

            if(StringUtils.isNotBlank(name)){
                criteria.and(Restrictions.like("name", name));
            }
            if(StringUtils.isNotBlank(address)){
                criteria.and(Restrictions.like("address", address));
            }
            if(StringUtils.isNotBlank(phone)){
                criteria.and(Restrictions.like("phone", phone));
            }
            if(ids != null && !ids.isEmpty()){
                criteria.and(Restrictions.in("id", ids));
            }

            List<Company> list  = mapper.find(criteria);
            Long          total = mapper.count(criteria);

            return new Page<Company>(list, total, pageRequest);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public List<Company> findOrganizations(List<String> companyIds, String departmentId, String positionId){
        try{
            Criteria criteria = new Criteria();

            if(companyIds != null && !companyIds.isEmpty()){
                criteria.and(Restrictions.in("c.id", companyIds));
            }
            if(StringUtils.isNotBlank(departmentId)){
                criteria.and(Restrictions.eq("d.id", departmentId));
            }
            if(StringUtils.isNotBlank(positionId)){
                criteria.and(Restrictions.eq("p.id", positionId));
            }

            return mapper.findOrganizations(criteria);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }
}

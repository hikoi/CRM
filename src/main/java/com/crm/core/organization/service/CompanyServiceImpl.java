package com.crm.core.organization.service;

import com.crm.commons.consts.CacheName;
import com.crm.core.authentication.entity.ServiceTicket;
import com.crm.core.organization.dao.CompanyDao;
import com.crm.core.organization.entity.Company;
import com.crm.core.permission.consts.ResourceType;
import com.crm.core.permission.dao.AccountPermissionDao;
import com.crm.core.permission.dao.PermissionDao;
import com.crm.core.permission.entity.Permission;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.wah.doraemon.security.request.Page;
import org.wah.doraemon.security.request.PageRequest;
import org.wah.doraemon.utils.ObjectUtils;
import org.wah.doraemon.utils.RedisUtils;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class CompanyServiceImpl implements CompanyService{

    @Autowired
    private CompanyDao companyDao;

    @Autowired
    private PermissionDao permissionDao;

    @Autowired
    private ShardedJedisPool pool;

    @Autowired
    private AccountPermissionDao accountPermissionDao;

    @Override
    @Transactional
    public void save(Company company){
        Assert.notNull(company, "公司信息不能为空");
        //保存公司信息
        companyDao.saveOrUpdate(company);

        //添加资源信息
        Permission permission = new Permission();
        permission.setResourceId(company.getId());
        permission.setType(ResourceType.COMPANY);
        permissionDao.save(permission);
    }

    @Override
    @Transactional
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
    public List<Company> findOrganizationsByTicket(String ticket){
        Assert.hasText(ticket, "帐户票据不能为空");

        try(ShardedJedis jedis = pool.getResource()){
            //查询票据
            ServiceTicket st = RedisUtils.get(jedis, CacheName.SERVICE_TICKET + ticket, ServiceTicket.class);
            //账户ID
            String accountId = st.getAccountId();
            //查询账户菜单权限
            List<Permission> permissions = accountPermissionDao.find(Arrays.asList(accountId), ResourceType.COMPANY);

            if(permissions != null && !permissions.isEmpty()){
                List<Company> list = companyDao.findOrganizations(ObjectUtils.properties(permissions, "resourceId", String.class),
                                                                  null, null);

                return list;
            }

            return null;
        }
    }
}

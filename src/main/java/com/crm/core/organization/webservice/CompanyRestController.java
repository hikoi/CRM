package com.crm.core.organization.webservice;

import com.crm.commons.consts.HeaderName;
import com.crm.core.organization.entity.Company;
import com.crm.core.organization.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.wah.doraemon.security.request.Page;
import org.wah.doraemon.security.request.PageRequest;
import org.wah.doraemon.security.response.Responsed;
import redis.clients.jedis.ShardedJedisPool;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/api/1.0/company")
public class CompanyRestController{

    @Autowired
    private CompanyService companyService;

    @Autowired
    private ShardedJedisPool pool;

    @RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Responsed save(@RequestBody Company company){
        companyService.save(company);

        return new Responsed<Company>("保存成功");
    }

    @RequestMapping(value = "", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Responsed update(@RequestBody Company company){
        companyService.update(company);

        return new Responsed<Company>("更新成功");
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Responsed<Company> getById(@PathVariable("id") String id){
        Company company = companyService.getById(id);

        return new Responsed<Company>("查询成功", company);
    }

    @RequestMapping(value = "/organization/ticket", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Responsed<List<Company>> findOrganizationsByTicket(HttpServletRequest request){
        String ticket = request.getHeader(HeaderName.TICKET);
        List<Company> list = companyService.findOrganizationsByTicket(ticket);

        return new Responsed<List<Company>>("查询成功", list);
    }
}

package com.crm.core.sensitive.webservice;

import com.crm.core.sensitive.entity.Sensitive;
import com.crm.core.sensitive.service.SensitiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.wah.doraemon.entity.consts.UsingState;
import org.wah.doraemon.security.request.Page;
import org.wah.doraemon.security.request.PageRequest;
import org.wah.doraemon.security.response.Responsed;

@RestController
@RequestMapping(value = "/api/1.0/sensitive")
public class SensitiveRestController{

    @Autowired
    private SensitiveService sensitiveService;

    @RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Responsed<Sensitive> save(@RequestBody Sensitive sensitive){
        sensitiveService.save(sensitive);

        return new Responsed<Sensitive>("保存成功", sensitive);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Responsed<Sensitive> update(@RequestBody Sensitive sensitive){
        sensitiveService.update(sensitive);

        return new Responsed<Sensitive>("更新成功", sensitive);
    }

    @RequestMapping(value = "/page", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Responsed<Page<Sensitive>> page(Long pageNum, Long pageSize, String content, UsingState state){
        PageRequest pageRequest = new PageRequest(pageNum, pageSize);
        Page page = sensitiveService.page(pageRequest, content, state);

        return new Responsed<Page<Sensitive>>("查询成功", page);
    }
}

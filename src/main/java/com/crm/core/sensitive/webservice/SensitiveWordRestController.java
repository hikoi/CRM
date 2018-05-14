package com.crm.core.sensitive.webservice;

import com.crm.core.sensitive.entity.SensitiveWord;
import com.crm.core.sensitive.service.SensitiveWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.wah.doraemon.entity.consts.UsingState;
import org.wah.doraemon.security.request.Page;
import org.wah.doraemon.security.request.PageRequest;
import org.wah.doraemon.security.response.Responsed;

import java.util.List;

@RestController
@RequestMapping(value = "/api/1.0/sensitive")
public class SensitiveWordRestController{

    @Autowired
    private SensitiveWordService sensitiveService;

    @RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Responsed<SensitiveWord> save(@RequestBody SensitiveWord sensitiveWord){
        sensitiveService.save(sensitiveWord);

        return new Responsed<SensitiveWord>("保存成功", sensitiveWord);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Responsed<SensitiveWord> update(@RequestBody SensitiveWord sensitiveWord){
        sensitiveService.update(sensitiveWord);

        return new Responsed<SensitiveWord>("更新成功", sensitiveWord);
    }

    @RequestMapping(value = "/page", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Responsed<Page<SensitiveWord>> page(Long pageNum, Long pageSize, String content, UsingState state){
        PageRequest pageRequest = new PageRequest(pageNum, pageSize);
        Page page = sensitiveService.page(pageRequest, content, state);

        return new Responsed<Page<SensitiveWord>>("查询成功", page);
    }

    @RequestMapping(value = "/regex/{wxno}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Responsed<String> getRegEx(@PathVariable("wxno") String wxno){
        String regEx = sensitiveService.getRegEx(wxno);

        return new Responsed<String>("查询成功", regEx);
    }

    @RequestMapping(value = "/relation/group/{groupId}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Responsed updateRelationByGroupId(@PathVariable("groupId") String groupId, List<String> sensitiveIds){
        sensitiveService.updateRelationByGroupId(groupId, sensitiveIds);

        return new Responsed("更新成功");
    }

    @RequestMapping(value = "/relation/wechat/{wechatId}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Responsed updateRelationByWechatId(@PathVariable("wechatId") String wechatId, List<String> sensitiveIds){
        sensitiveService.updateRelationByWechatId(wechatId, sensitiveIds);

        return new Responsed("更新成功");
    }
}

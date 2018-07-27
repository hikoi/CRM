package com.crm.core.group.webservice;

import com.crm.core.group.service.GroupsWordService;
import com.crm.core.words.entity.Word;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.wah.doraemon.entity.consts.UsingState;
import org.wah.doraemon.security.request.Page;
import org.wah.doraemon.security.request.PageRequest;
import org.wah.doraemon.security.response.Responsed;

@RestController
@RequestMapping(value = "/api/1.0/groups/word")
public class GroupsWordRestController{

    @Autowired
    private GroupsWordService groupsWordService;

    @RequestMapping(value = "/page", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Responsed<Page<Word>> page(Long pageNum, Long pageSize, String groupsId, String wechatId, UsingState groupsState){
        PageRequest pageRequest = new PageRequest(pageNum, pageSize);
        Page<Word> page = groupsWordService.page(pageRequest, groupsId, wechatId, groupsState);

        return new Responsed<Page<Word>>("查询成功", page);
    }
}
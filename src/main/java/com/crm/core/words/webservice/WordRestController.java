package com.crm.core.words.webservice;

import com.crm.core.words.entity.Word;
import com.crm.core.words.service.WordService;
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
@RequestMapping(value = "/api/1.0/word")
public class WordRestController{

    @Autowired
    private WordService wordService;

    @RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Responsed save(@RequestBody Word word){
        wordService.save(word);

        return new Responsed("保存成功");
    }

    @RequestMapping(value = "", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Responsed update(@RequestBody Word word){
        wordService.update(word);

        return new Responsed("更新成功");
    }

    @RequestMapping(value = "/page", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Responsed<Page<Word>> page(Long pageNum, Long pageSize, String content, UsingState state){
        PageRequest pageRequest = new PageRequest(pageNum, pageSize);
        Page<Word> page = wordService.page(pageRequest, content, state);

        return new Responsed<Page<Word>>("查询成功", page);
    }
}

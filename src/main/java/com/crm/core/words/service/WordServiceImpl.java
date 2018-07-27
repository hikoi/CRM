package com.crm.core.words.service;

import com.crm.core.words.dao.WordDao;
import com.crm.core.words.entity.Word;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.wah.doraemon.entity.consts.UsingState;
import org.wah.doraemon.security.request.Page;
import org.wah.doraemon.security.request.PageRequest;

@Service
@Transactional(readOnly = true)
public class WordServiceImpl implements WordService{

    @Autowired
    private WordDao wordDao;

    @Override
    @Transactional
    public void save(Word word){
        Assert.notNull(word, "敏感词信息不能为空");
        Assert.hasText(word.getContent(), "敏感词内容不能为空");

        wordDao.saveOrUpdate(word);
    }

    @Override
    @Transactional
    public void update(Word word){
        Assert.notNull(word, "敏感词信息不能为空");
        Assert.hasText(word.getId(), "敏感词ID不能为空");

        wordDao.saveOrUpdate(word);
    }

    @Override
    public Page<Word> page(PageRequest pageRequest, String content, UsingState state){
        Assert.notNull(pageRequest, "分页信息不能为空");

        return wordDao.page(pageRequest, content, state);
    }
}

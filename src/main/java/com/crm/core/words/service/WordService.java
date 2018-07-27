package com.crm.core.words.service;

import com.crm.core.words.entity.Word;
import org.wah.doraemon.entity.consts.UsingState;
import org.wah.doraemon.security.request.Page;
import org.wah.doraemon.security.request.PageRequest;

public interface WordService{

    void save(Word word);

    void update(Word word);

    Page<Word> page(PageRequest pageRequest, String content, UsingState state);
}

package com.crm.core.sensitive.service;

import com.crm.core.sensitive.entity.SensitiveWord;
import org.wah.doraemon.entity.consts.UsingState;
import org.wah.doraemon.security.request.Page;
import org.wah.doraemon.security.request.PageRequest;

public interface SensitiveWordService{

    void save(SensitiveWord sensitiveWord);

    void update(SensitiveWord sensitiveWord);

    SensitiveWord getById(String id);

    Page<SensitiveWord> page(PageRequest pageRequest, String content, UsingState state);

    String getRegEx(String wxno);
}

package com.crm.core.sensitive.service;

import com.crm.core.sensitive.entity.Sensitive;
import org.wah.doraemon.entity.consts.UsingState;
import org.wah.doraemon.security.request.Page;
import org.wah.doraemon.security.request.PageRequest;

public interface SensitiveService{

    void save(Sensitive sensitive);

    void update(Sensitive sensitive);

    Sensitive getById(String id);

    Page<Sensitive> page(PageRequest pageRequest, String content, UsingState state);
}

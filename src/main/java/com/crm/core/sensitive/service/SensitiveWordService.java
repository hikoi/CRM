package com.crm.core.sensitive.service;

import com.crm.core.sensitive.entity.SensitiveWord;
import org.wah.doraemon.entity.consts.UsingState;
import org.wah.doraemon.security.request.Page;
import org.wah.doraemon.security.request.PageRequest;

import java.util.List;

public interface SensitiveWordService{

    void save(SensitiveWord sensitiveWord);

    void update(SensitiveWord sensitiveWord);

    SensitiveWord getById(String id);

    Page<SensitiveWord> page(PageRequest pageRequest, String content, UsingState state);

    String getRegEx(String wxno);

    void updateRelationByGroupId(String groupId, List<String> sensitiveIds);

    void updateRelationByWechatId(String wechatId, List<String> sensitiveIds);
}

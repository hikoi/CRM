package com.crm.core.group.service;

import com.crm.core.words.entity.Word;
import org.wah.doraemon.entity.consts.UsingState;
import org.wah.doraemon.security.request.Page;
import org.wah.doraemon.security.request.PageRequest;

public interface GroupsWordService{

    Page<Word> page(PageRequest pageRequest, String groupsId, String wechatId, UsingState groupsState);
}

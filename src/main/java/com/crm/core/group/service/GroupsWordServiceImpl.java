package com.crm.core.group.service;

import com.crm.core.group.dao.GroupsWordDao;
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
public class GroupsWordServiceImpl implements GroupsWordService{

    @Autowired
    private GroupsWordDao groupsWordDao;

    @Override
    public Page<Word> page(PageRequest pageRequest, String groupsId, String wechatId, UsingState groupsState){
        Assert.notNull(pageRequest, "分页信息不能为空");

        return groupsWordDao.page(pageRequest, groupsId, wechatId, groupsState);
    }
}

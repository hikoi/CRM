package com.crm.core.sensitive.service;

import com.crm.commons.utils.CharUtils;
import com.crm.core.sensitive.dao.SensitiveWordDao;
import com.crm.core.sensitive.entity.SensitiveWord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.wah.doraemon.entity.consts.UsingState;
import org.wah.doraemon.security.request.Page;
import org.wah.doraemon.security.request.PageRequest;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class SensitiveWordServiceImpl implements SensitiveWordService{

    @Autowired
    private SensitiveWordDao sensitiveWordDao;

    @Override
    @Transactional(readOnly = false)
    public void save(SensitiveWord sensitiveWord){
        Assert.notNull(sensitiveWord, "敏感词信息不能为空");

        sensitiveWordDao.saveOrUpdate(sensitiveWord);
    }

    @Override
    @Transactional(readOnly = false)
    public void update(SensitiveWord sensitiveWord){
        Assert.notNull(sensitiveWord, "敏感词信息不能为空");
        Assert.hasText(sensitiveWord.getId(), "敏感词信息不能为空");

        sensitiveWordDao.saveOrUpdate(sensitiveWord);
    }

    @Override
    public SensitiveWord getById(String id){
        Assert.hasText(id, "敏感词ID不能为空");

        return sensitiveWordDao.getById(id);
    }

    @Override
    public Page<SensitiveWord> page(PageRequest pageRequest, String content, UsingState state){
        Assert.notNull(pageRequest, "分页信息不能为空");

        return sensitiveWordDao.page(pageRequest, content, state);
    }

    @Override
    public String getRegEx(String wxno){
        Assert.hasText(wxno, "微信号不能为空");

        //根据微信号查询设备所有敏感词
        List<SensitiveWord> sensitives = sensitiveWordDao.findByWxno(wxno, UsingState.USABLE);

        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < sensitives.size(); i++){
            if(i > 0){
                sb.append('|');
            }
            sb.append(CharUtils.toUnicode(sensitives.get(i).getContent()));
        }

        return sb.toString();
    }
}

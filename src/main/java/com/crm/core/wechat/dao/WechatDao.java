package com.crm.core.wechat.dao;

import com.crm.core.wechat.dao.mapper.WechatMapper;
import com.crm.core.wechat.entity.Wechat;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import org.wah.doraemon.security.exception.DataAccessException;
import org.wah.doraemon.security.request.Page;
import org.wah.doraemon.security.request.PageRequest;
import org.wah.doraemon.utils.IDGenerator;
import org.wah.doraemon.utils.mybatis.Criteria;
import org.wah.doraemon.utils.mybatis.Restrictions;

import java.util.Date;
import java.util.List;

@Repository
public class WechatDao{

    private Logger logger = LoggerFactory.getLogger(WechatDao.class);

    @Autowired
    private WechatMapper mapper;

    public void saveOrUpdate(Wechat wechat){
        try{
            Assert.notNull(wechat, "微信信息不能为空");

            if(StringUtils.isBlank(wechat.getId())){
                Assert.hasText(wechat.getWxno(), "微信号不能为空");

                wechat.setId(IDGenerator.uuid32());
                wechat.setCreateTime(new Date());
                mapper.save(wechat);
            }else{
                wechat.setUpdateTime(new Date());
                mapper.update(wechat);
            }
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public Wechat getById(String id){
        try{
            Assert.hasText(id, "微信ID不能为空");

            Criteria criteria = new Criteria();
            criteria.and(Restrictions.eq("id", id));

            return mapper.get(criteria);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public Wechat getByWxno(String wxno){
        try{
            Assert.hasText(wxno, "微信号不能为空");

            Criteria criteria = new Criteria();
            criteria.and(Restrictions.eq("wxno", wxno));

            return mapper.get(criteria);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public boolean exist(String wxno){
        try{
            Assert.hasText(wxno, "微信号不能为空");

            Criteria criteria = new Criteria();
            criteria.and(Restrictions.eq("wxno", wxno));

            return (mapper.count(criteria) > 0);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public Page<Wechat> page(PageRequest pageRequest, String accountId, String wxno, String nickname){
        try{
            Assert.notNull(pageRequest, "分页信息不能为空");

            Criteria criteria = new Criteria();
            criteria.limit(Restrictions.limit(pageRequest.getOffset(), pageRequest.getPageSize()));
            criteria.sort(Restrictions.desc("createTime"));

            if(StringUtils.isNotBlank(accountId)){
                criteria.and(Restrictions.eq("accountId", accountId));
            }
            if(StringUtils.isNotBlank(wxno)){
                criteria.and(Restrictions.like("wxno", wxno));
            }
            if(StringUtils.isNotBlank(nickname)){
                criteria.and(Restrictions.like("nickname", nickname));
            }

            List<Wechat> list = mapper.find(criteria);
            Long total = mapper.count(criteria);

            return new Page<Wechat>(list, total, pageRequest);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }
}

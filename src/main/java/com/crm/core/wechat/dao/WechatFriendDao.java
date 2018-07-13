package com.crm.core.wechat.dao;

import com.crm.core.wechat.dao.mapper.WechatFriendMapper;
import com.crm.core.wechat.entity.Wechat;
import com.crm.core.wechat.entity.WechatFriend;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import org.wah.doraemon.domain.Createable;
import org.wah.doraemon.security.exception.DataAccessException;
import org.wah.doraemon.security.request.Page;
import org.wah.doraemon.security.request.PageRequest;
import org.wah.doraemon.utils.IDGenerator;
import org.wah.doraemon.utils.mybatis.Criteria;
import org.wah.doraemon.utils.mybatis.Restrictions;

import java.util.Date;
import java.util.List;

@Repository
public class WechatFriendDao{

    private Logger logger = LoggerFactory.getLogger(WechatFriendDao.class);

    @Autowired
    private WechatFriendMapper mapper;

    public void saveOrUpdate(WechatFriend friend){
        try{
            Assert.notNull(friend, "微信好友信息不能为空");

            if(StringUtils.isBlank(friend.getId())){
                Assert.hasText(friend.getWechatId(), "微信好友所属微信ID不能为空");
                Assert.hasText(friend.getWxid(), "微信wxid不能为空");
                Assert.notNull(friend.getType(), "微信好友类型不能为空");

                friend.setId(IDGenerator.uuid32());
                friend.setCreateTime(new Date());
                mapper.save(friend);
            }else{
                friend.setUpdateTime(new Date());
                mapper.update(friend);
            }
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public void saveList(List<WechatFriend> friends){
        try{
            Assert.notEmpty(friends, "微信好友列表不能为空");

            final Date now = new Date();

            for(WechatFriend friend : friends){
                Assert.notNull(friend, "微信好友信息不能为空");
                Assert.hasText(friend.getWechatId(), "微信好友所属微信ID不能为空");
                Assert.hasText(friend.getWxid(), "微信wxid不能为空");
                Assert.notNull(friend.getType(), "微信好友类型不能为空");

                friend.setId(IDGenerator.uuid32());
                friend.setCreateTime(now);
            }

            mapper.saveList(friends);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public void updateList(List<WechatFriend> friends){
        try{
            Assert.notEmpty(friends, "微信好友列表不能为空");

            final Date now = new Date();

            for(WechatFriend friend : friends){
                Assert.notNull(friend, "微信好友信息不能为空");
                Assert.hasText(friend.getWxid(), "微信好友wxid不能为空");
                Assert.notNull(friend.getType(), "微信好友类型不能为空");
                Assert.notNull(friend.getType(), "微信好友类型不能为空");

                friend.setUpdateTime(now);
            }

            mapper.updateList(friends);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public WechatFriend getById(String id){
        try{
            Assert.hasText(id, "微信好友ID不能为空");

            Criteria criteria = new Criteria();
            criteria.and(Restrictions.eq("id", id));

            return mapper.get(criteria);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public List<WechatFriend> find(String sellerId, String wechatId, String wxid, String wxno, String nickname){
        try{
            Criteria criteria = new Criteria();
            criteria.sort(Restrictions.desc("createTime"));

            if(StringUtils.isNotBlank(sellerId)){
                criteria.and(Restrictions.eq("sellerId", sellerId));
            }

            if(StringUtils.isNotBlank(wechatId)){
                criteria.and(Restrictions.eq("wechatId", wechatId));
            }
            if(StringUtils.isNotBlank(wxid)){
                criteria.and(Restrictions.eq("wxid", wxid));
            }
            if(StringUtils.isNotBlank(wxno)){
                criteria.and(Restrictions.like("wxno", wxno));
            }
            if(StringUtils.isNotBlank(nickname)){
                criteria.and(Restrictions.like("nickname", nickname));
            }

            return mapper.find(criteria);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public Page<WechatFriend> page(PageRequest pageRequest, String sellerId, String wechatId, String wxid, String wxno, String nickname){
        try{
            Assert.notNull(pageRequest, "分页信息不能为空");

            Criteria criteria = new Criteria();
            criteria.limit(Restrictions.limit(pageRequest.getOffset(), pageRequest.getPageSize()));
            criteria.sort(Restrictions.desc("createTime"));

            if(StringUtils.isNotBlank(sellerId)){
                criteria.and(Restrictions.eq("sellerId", sellerId));
            }
            if(StringUtils.isNotBlank(wechatId)){
                criteria.and(Restrictions.eq("wechatId", wechatId));
            }
            if(StringUtils.isNotBlank(wxid)){
                criteria.and(Restrictions.eq("wxid", wxid));
            }
            if(StringUtils.isNotBlank(wxno)){
                criteria.and(Restrictions.like("wxno", wxno));
            }
            if(StringUtils.isNotBlank(nickname)){
                criteria.and(Restrictions.like("nickname", nickname));
            }

            List<WechatFriend> list = mapper.find(criteria);
            Long total = mapper.count(criteria);

            return new Page<WechatFriend>(list, total, pageRequest);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public List<WechatFriend> findByWechatId(String wechatId){
        try{
            Assert.hasText(wechatId, "微信好友所属微信ID不能为空");

            Criteria criteria = new Criteria();
            criteria.sort(Restrictions.desc("createTime"));
            criteria.and(Restrictions.eq("wechatId", wechatId));

            return mapper.find(criteria);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public boolean exist(String wechatId, String wxid){
        try{
            Assert.hasText(wechatId, "微信好友所属微信ID不能为空");
            Assert.hasText(wxid, "微信好友wxid不能为空");

            Criteria criteria = new Criteria();
            criteria.and(Restrictions.eq("wechatId", wechatId));
            criteria.and(Restrictions.eq("wxid", wxid));

            return (mapper.count(criteria) > 0);
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }
}

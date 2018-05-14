package com.crm.core.wechat.service;

import com.crm.core.wechat.dao.WechatFriendDao;
import com.crm.core.wechat.entity.Wechat;
import com.crm.core.wechat.entity.WechatFriend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.wah.doraemon.security.request.Page;
import org.wah.doraemon.security.request.PageRequest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class WechatFriendServiceImpl implements WechatFriendService{

    @Autowired
    private WechatFriendDao wechatFriendDao;

    @Override
    @Transactional(readOnly = false)
    public void save(String wechatId, WechatFriend friend){
        Assert.hasText(wechatId, "微信好友所属微信ID不能为空");
        Assert.notNull(friend, "微信好友信息不能为空");
        Assert.hasText(friend.getWxid(), "微信好友wxid不能为空");

        if(wechatFriendDao.exist(wechatId, friend.getWxid())){
            //存在
            wechatFriendDao.updateList(Arrays.asList(friend));
        }else{
            friend.setWechatId(wechatId);
            wechatFriendDao.saveOrUpdate(friend);
        }
    }

    @Override
    @Transactional(readOnly = false)
    public void synchronize(String wechatId, List<WechatFriend> friends){
        Assert.hasText(wechatId, "微信好友所属微信ID不能为空");

        List<WechatFriend> saveList = new ArrayList<WechatFriend>();
        List<WechatFriend> updateList = new ArrayList<WechatFriend>();

        //好友列表
        List<WechatFriend> original = wechatFriendDao.findByWechatId(wechatId);

        if(original == null || original.isEmpty()){
            //没有好友列表
            for(WechatFriend friend : friends){
                Assert.hasText(friend.getWxid(), "微信好友wxid不能为空");

                friend.setWechatId(wechatId);
            }

            wechatFriendDao.saveList(friends);
        }else{
            for(WechatFriend friend : friends){
                Assert.hasText(friend.getWxid(), "微信好友wxid不能为空");

                friend.setWechatId(wechatId);

                if(original.contains(friend)){
                    //原有好友
                    updateList.add(friend);
                }else{
                    //新增好友
                    saveList.add(friend);
                }
            }

            //更新
            if(!updateList.isEmpty()){
                wechatFriendDao.updateList(updateList);
            }
            //保存
            if(!saveList.isEmpty()){
                wechatFriendDao.saveList(saveList);
            }
        }
    }

    @Override
    public Page<WechatFriend> page(PageRequest pageRequest, String id, String wechatId, String wxid, String wxno, String nickname){
        Assert.notNull(pageRequest, "分页信息不能为空");

        return wechatFriendDao.page(pageRequest, id, wechatId, wxid, wxno, nickname);
    }
}

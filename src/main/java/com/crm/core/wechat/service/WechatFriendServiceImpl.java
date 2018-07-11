package com.crm.core.wechat.service;

import com.crm.core.wechat.consts.WechatFriendType;
import com.crm.core.wechat.dao.WechatDao;
import com.crm.core.wechat.dao.WechatFriendDao;
import com.crm.core.wechat.entity.Wechat;
import com.crm.core.wechat.entity.WechatFriend;
import org.apache.commons.lang3.StringUtils;
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

    @Autowired
    private WechatDao wechatDao;

    @Override
    @Transactional(readOnly = false)
    public void save(String wxno, WechatFriend friend){
        Assert.hasText(wxno, "所属微信号不能为空");
        Assert.notNull(friend, "微信好友信息不能为空");
        Assert.notNull(friend.getType(), "微信好友类型不能为空");
        Assert.hasText(friend.getWxid(), "微信好友wxid不能为空");

        Wechat wechat = wechatDao.getByWxno(wxno);
        String wechatId = wechat.getId();

        //判断微信好友类型
        if(StringUtils.isNotBlank(friend.getWxid())){
            if(friend.getWxid().startsWith("gh_")){
                friend.setType(WechatFriendType.SUBSCRIPTION);

            }else if(friend.getWxid().equals("filehelper") || friend.getWxid().equals("weixin")){
                friend.setType(WechatFriendType.HELPER);

            }else{
                friend.setType(WechatFriendType.FRIEND);
            }
        }else{
            friend.setType(WechatFriendType.OTHER);
        }

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
    public void synchronize(String wxno, List<WechatFriend> friends){
        Assert.hasText(wxno, "所属微信号不能为空");

        List<WechatFriend> saveList = new ArrayList<WechatFriend>();
        List<WechatFriend> updateList = new ArrayList<WechatFriend>();

        Wechat wechat = wechatDao.getByWxno(wxno);
        String wechatId = wechat.getId();

        //好友列表
        List<WechatFriend> original = wechatFriendDao.findByWechatId(wechatId);

        for(WechatFriend friend : friends){
            Assert.notNull(friend, "微信好友信息不能为空");
            Assert.hasText(friend.getWxid(), "微信好友wxid不能为空");

            friend.setWechatId(wechatId);

            //判断微信好友类型
            if(StringUtils.isNotBlank(friend.getWxid())){
                if(friend.getWxid().startsWith("gh_")){
                    friend.setType(WechatFriendType.SUBSCRIPTION);

                }else if(friend.getWxid().equals("filehelper") || friend.getWxid().equals("weixin")){
                    friend.setType(WechatFriendType.HELPER);

                }else{
                    friend.setType(WechatFriendType.FRIEND);
                }
            }else{
                friend.setType(WechatFriendType.OTHER);
            }

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

    @Override
    public Page<WechatFriend> page(PageRequest pageRequest, String id, String wechatId, String wxid, String wxno, String nickname){
        Assert.notNull(pageRequest, "分页信息不能为空");

        return wechatFriendDao.page(pageRequest, id, wechatId, wxid, wxno, nickname);
    }
}

package com.crm.core.group.service;

import com.crm.commons.consts.CacheName;
import com.crm.core.authentication.entity.ServiceTicket;
import com.crm.core.group.dao.GroupsWechatFriendDao;
import com.crm.core.group.entity.GroupsWechat;
import com.crm.core.group.entity.GroupsWechatFriend;
import com.crm.core.wechat.consts.WechatFriendType;
import com.crm.core.wechat.dao.WechatDao;
import com.crm.core.wechat.entity.Wechat;
import com.crm.core.wechat.entity.WechatFriend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.wah.doraemon.entity.consts.UsingState;
import org.wah.doraemon.utils.RedisUtils;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(readOnly = true)
public class GroupsWechatFriendServiceImpl implements GroupsWechatFriendService{

    @Autowired
    private GroupsWechatFriendDao groupsWechatFriendDao;

    @Autowired
    private WechatDao wechatDao;

    @Autowired
    private ShardedJedisPool pool;

    @Override
    @Transactional
    public void save(String newGroupsId, String oldGroupsId, String friendId){
        Assert.hasText(newGroupsId, "新分组ID不能为空");
        Assert.hasText(oldGroupsId, "旧分组ID不能为空");
        Assert.hasText(friendId, "微信好友ID不能为空");

        //删除旧分组信息
        groupsWechatFriendDao.deleteByGroupsIdAndFriendId(oldGroupsId, friendId);
        //添加新分组信息
        groupsWechatFriendDao.save(newGroupsId, friendId);
    }

    @Override
    public List<Map<String, Object>> findByTicket(String ticket){
        Assert.hasText(ticket, "登录凭证不能为空");

        try(ShardedJedis jedis = pool.getResource()){
            ServiceTicket st = RedisUtils.get(jedis, CacheName.SERVICE_TICKET + ticket, ServiceTicket.class);

            List<GroupsWechatFriend> groupsFriends = groupsWechatFriendDao.find(null, st.getAccountId(), null, null, UsingState.USABLE, WechatFriendType.FRIEND);
            Map<String, Map<String, GroupsWechatFriend>> wechatGroups = new HashMap<String, Map<String, GroupsWechatFriend>>();

            if(groupsFriends != null && !groupsFriends.isEmpty()){
                for(GroupsWechatFriend group : groupsFriends){
                    for(WechatFriend friend : group.getFriends()){
                        String wechatId = friend.getWechatId();
                        String groupId  = group.getGroupsId();

                        //微信号内所有分组
                        if(!wechatGroups.containsKey(wechatId)){
                            Map<String, GroupsWechatFriend> friends = new HashMap<String, GroupsWechatFriend>();
                            wechatGroups.put(wechatId, friends);
                        }

                        Map<String, GroupsWechatFriend> friends = wechatGroups.get(wechatId);
                        if(!friends.containsKey(groupId)){
                            GroupsWechatFriend friendTemp = new GroupsWechatFriend();
                            friendTemp.setGroupsId(groupId);
                            friendTemp.setGroupsName(group.getGroupsName());
                            friendTemp.setFriends(new ArrayList<WechatFriend>());
                            friends.put(groupId, friendTemp);
                        }

                        GroupsWechatFriend friendTemp = friends.get(groupId);
                        friendTemp.getFriends().add(friend);
                    }
                }
            }

            //结果集合
            List<Map<String, Object>> target = new ArrayList<Map<String, Object>>();

            if(!wechatGroups.isEmpty()){
                for(Map.Entry<String, Map<String, GroupsWechatFriend>> entry : wechatGroups.entrySet()){
                    Map<String, Object> temp = new HashMap<String, Object>();
                    //微信ID
                    String wechatId = entry.getKey();
                    Wechat wechat = wechatDao.getById(wechatId);
                    temp.put("wechatId", wechat.getId());
                    temp.put("wxno", wechat.getWxno());
                    temp.put("nickname", wechat.getNickname());
                    //好友分组
                    Map<String, GroupsWechatFriend> groupsTemp = entry.getValue();
                    temp.put("groups", new ArrayList<GroupsWechatFriend>(groupsTemp.values()));
                    target.add(temp);
                }
            }

            return target;
        }
    }
}

package com.crm.core.wechat.service;

import com.crm.commons.consts.IMConfig;
import com.crm.core.group.consts.AllocationType;
import com.crm.core.group.dao.AllocationRuleDao;
import com.crm.core.group.dao.GroupsSellerDao;
import com.crm.core.group.dao.GroupsWechatDao;
import com.crm.core.group.entity.AllocationRule;
import com.crm.core.group.entity.GroupsSeller;
import com.crm.core.group.entity.GroupsWechat;
import com.crm.core.im.consts.IMMessageType;
import com.crm.core.im.entity.IMMessage;
import com.crm.core.im.utils.IMMessageUtils;
import com.crm.core.im.utils.IMUtils;
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
import org.wah.doraemon.entity.consts.UsingState;
import org.wah.doraemon.security.request.Page;
import org.wah.doraemon.security.request.PageRequest;
import org.wah.doraemon.utils.GsonUtils;
import org.wah.doraemon.utils.ObjectUtils;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
@Transactional(readOnly = true)
public class WechatFriendServiceImpl implements WechatFriendService{

    @Autowired
    private WechatFriendDao wechatFriendDao;

    @Autowired
    private WechatDao wechatDao;

    @Autowired
    private GroupsWechatDao groupsWechatDao;

    @Autowired
    private GroupsSellerDao groupsSellerDao;

    @Autowired
    private AllocationRuleDao allocationRuleDao;

    @Override
    @Transactional
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

        //好友分配
        if(WechatFriendType.FRIEND.equals(friend.getType())){
            //查询分组
            List<GroupsWechat> wechatGroups = groupsWechatDao.find(null, wechat.getId(), null,
                    null, UsingState.USABLE, null);
            //分组ID
            List<String> wechatGroupsIds = ObjectUtils.properties(wechatGroups, "groupsId", String.class);

            //销售ID
            List<String> sellerIds = new ArrayList<String>();
            //销售分组ID
            List<String> sellerGroupsIds = new ArrayList<String>();

            //查询分配规则
            List<AllocationRule> rules = allocationRuleDao.find(UsingState.USABLE, null, wechatGroupsIds);
            if(rules != null && !rules.isEmpty()){
                for(AllocationRule rule : rules){
                    if(AllocationType.BY_GROUP.equals(rule.getType())){
                        sellerGroupsIds.addAll(rule.getRegions());

                    }else if(AllocationType.BY_SELLER.equals(rule.getType())){
                        sellerIds.addAll(rule.getRegions());
                    }
                }
            }

            //查询销售ID
            List<GroupsSeller> sellerGroups = groupsSellerDao.find(null, null, sellerGroupsIds,
                    null, UsingState.USABLE, null,
                    null, null);

            if(sellerGroups != null && !sellerGroups.isEmpty()){
                for(GroupsSeller seller : sellerGroups){
                    sellerIds.addAll(ObjectUtils.properties(seller.getSellers(), "id", String.class));
                }
            }

            //分配销售
            if(sellerIds != null && !sellerIds.isEmpty()){
                Random random = new Random();
                int index = random.nextInt(sellerIds.size());

                //更新好友销售
                friend.setSellerId(sellerIds.get(index));
                wechatFriendDao.saveOrUpdate(friend);
            }
        }
    }

    @Override
    @Transactional
    public void synchronize(String wxno, List<WechatFriend> friends){
        Assert.hasText(wxno, "所属微信号不能为空");

        List<WechatFriend> saveList   = new ArrayList<WechatFriend>();
        List<WechatFriend> updateList = new ArrayList<WechatFriend>();

        Wechat wechat   = wechatDao.getByWxno(wxno);
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
            for(WechatFriend friend : saveList){
                //好友分配
                if(WechatFriendType.FRIEND.equals(friend.getType())){
                    //查询分组
                    List<GroupsWechat> wechatGroups = groupsWechatDao.find(null, wechat.getId(), null,
                                                                           null, UsingState.USABLE, null);
                    //分组ID
                    List<String> wechatGroupsIds = ObjectUtils.properties(wechatGroups, "groupsId", String.class);

                    //销售ID
                    List<String> sellerIds = new ArrayList<String>();
                    //销售分组ID
                    List<String> sellerGroupsIds = new ArrayList<String>();

                    //查询分配规则
                    List<AllocationRule> rules = allocationRuleDao.find(UsingState.USABLE, null, wechatGroupsIds);
                    if(rules != null && !rules.isEmpty()){
                        for(AllocationRule rule : rules){
                            if(AllocationType.BY_GROUP.equals(rule.getType())){
                                sellerGroupsIds.addAll(rule.getRegions());
                            }else if(AllocationType.BY_SELLER.equals(rule.getType())){
                                sellerIds.addAll(rule.getRegions());
                            }
                        }
                    }

                    //查询销售ID
                    List<GroupsSeller> sellerGroups = groupsSellerDao.find(null, null, sellerGroupsIds,
                                                                           null, UsingState.USABLE, null,
                                                                           null, null);

                    if(sellerGroups != null && !sellerGroups.isEmpty()){
                        for(GroupsSeller seller : sellerGroups){
                            sellerIds.addAll(ObjectUtils.properties(seller.getSellers(), "accountId", String.class));
                        }
                    }

                    //分配销售
                    if(sellerIds != null && !sellerIds.isEmpty()){
                        Random random = new Random();
                        int index = random.nextInt(sellerIds.size());

                        //更新好友销售
                        friend.setSellerId(sellerIds.get(index));
                    }
                }
            }

            wechatFriendDao.saveList(saveList);
        }
    }

    @Override
    public Page<WechatFriend> page(PageRequest pageRequest, String sellerId, String wechatId, String wxid, String wxno,
                                   String nickname){
        Assert.notNull(pageRequest, "分页信息不能为空");

        return wechatFriendDao.page(pageRequest, sellerId, wechatId, wxid, wxno, nickname);
    }

    @Override
    @Transactional
    public void redistribution(String id, String toAccount){
        Assert.hasText(id, "微信好友ID不能为空");

        WechatFriend friend = wechatFriendDao.getById(id);
        String fromAccount = friend.getSellerId();

        friend.setSellerId(StringUtils.isBlank(toAccount) ? "" : toAccount);

        if(StringUtils.isNotBlank(fromAccount)){
            //发送通知
            IMMessage deleteAdvice = IMMessageUtils.createCustomMsg(null, fromAccount, IMMessageType.TIM_DELETE_FRIEND_ELEM,
                                                                    "好友移除通知", friend.getId(),
                                                                    MessageFormat.format("好友[{0}{1}]已从你的列表中移除",
                                                                                         friend.getNickname(),
                                                                                         friend.getRemarkname()));

            IMUtils.sendMsg(IMConfig.SDK_APPID, IMConfig.ADMINISTRATOR, IMConfig.ADMINISTRATOR_SIG, deleteAdvice);
        }

        if(StringUtils.isNotBlank(toAccount)){
            IMMessage addAdvice = IMMessageUtils.createCustomMsg(null, toAccount, IMMessageType.TIM_ADD_FRIEND_ELEM,
                                                                 "好友新增通知", GsonUtils.serialize(friend),
                                                                 MessageFormat.format("好友[{0}{1}]已添加到你的列表中",
                                                                                      friend.getNickname(),
                                                                                      friend.getRemarkname()));

            IMUtils.sendMsg(IMConfig.SDK_APPID, IMConfig.ADMINISTRATOR, IMConfig.ADMINISTRATOR_SIG, addAdvice);
        }

        wechatFriendDao.saveOrUpdate(friend);
    }
}

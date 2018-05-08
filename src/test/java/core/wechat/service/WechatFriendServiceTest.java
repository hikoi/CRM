package core.wechat.service;

import com.crm.core.wechat.entity.WechatFriend;
import com.crm.core.wechat.service.WechatFriendService;
import com.google.gson.reflect.TypeToken;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.wah.doraemon.utils.GsonUtils;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml"})
@ActiveProfiles(value = "test")
public class WechatFriendServiceTest{

    @Autowired
    private WechatFriendService wechatFriendService;

    @Test
    public void save(){
       String wechatId = "961fd3c5f8914c5796ae2c76cb057c2f";

        WechatFriend friend = new WechatFriend();
        friend.setNickname("NA");
        friend.setWxid("wxid_98m3ys9svjnu22");
        friend.setWxno("hsl_test");

        wechatFriendService.save(wechatId, friend);
    }

    @Test
    public void synchronize(){
        String wechatId = "961fd3c5f8914c5796ae2c76cb057c2f";
        String json = "[{\"nickname\":\"NA\",\"remark\":\"\",\"wxid\":\"wxid_98m3ys9svjnu22\",\"wxno\":\"hsl_test\"},{\"nickname\":\"A0000000000-长沙正显科技\",\"remark\":\"fee4315e6bb24d009f00a142e9df3f80\",\"wxid\":\"wxid_kkw0o2v2beqc22\",\"wxno\":\"cszxkj\"},{\"nickname\":\"麦仁\",\"remark\":\"8e3aabc6302841ccac0e5d8ff22f0b51\",\"wxid\":\"a27340459\",\"wxno\":\"myron20\"},{\"nickname\":\"Bacon Chen\",\"remark\":\"400933e27bf142c8a52f1c3cd93313f2\",\"wxid\":\"wxid_oxx6r9g9t9a721\",\"wxno\":\"Bacon-Chen\"},{\"nickname\":\"黎海桦\",\"remark\":\"94605be9562e45a1a598e50b965adb53\",\"wxid\":\"wxid_6j7bhfxp4fry22\",\"wxno\":\"Lyh_HoiRab\"},{\"nickname\":\"Temper 司徒\",\"remark\":\"\",\"wxid\":\"wxid_zx2jsufjz9cf21\",\"wxno\":\"tempersitu\"}]";

        List<WechatFriend> friends = GsonUtils.deserialize(json, new TypeToken<List<WechatFriend>>(){}.getType());

        wechatFriendService.synchronize(wechatId, friends);
    }
}

package core.wechat.service;

import com.crm.core.wechat.entity.WechatFriend;
import com.crm.core.wechat.service.WechatFriendService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml", "classpath:springbeans.xml"})
@ActiveProfiles(value = "test")
public class WechatFriendServiceTest{

    @Autowired
    private WechatFriendService wechatFriendService;

    @Test
    public void redistribution(){
        String friendId = "0c7c0777848c4c75aaec6e2b0700512d";

        wechatFriendService.redistribution(friendId, null);
    }

    @Test
    public void synchronize(){
        WechatFriend friend1 = new WechatFriend();
        friend1.setWxid("wxid_6j7bhfxp4fry22");
        friend1.setWxno("Lyh_HoiRab");
        friend1.setNickname("黎海桦");

        WechatFriend friend2 = new WechatFriend();
        friend2.setWxid("a27340459");
        friend2.setWxno("myron20");
        friend2.setNickname("麦仁");

        List<WechatFriend> friends = Arrays.asList(friend1, friend2);
        String wxno = "hsl_test";

        wechatFriendService.synchronize(wxno, friends);
    }
}

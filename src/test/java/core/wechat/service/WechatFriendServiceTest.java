package core.wechat.service;

import com.crm.core.wechat.service.WechatFriendService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
}

package core.wechat.service;

import com.crm.core.wechat.entity.WechatMessage;
import com.crm.core.wechat.service.WechatMessageService;
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
public class WechatMessageServiceTest{

    @Autowired
    private WechatMessageService wechatMessageService;

    @Test
    public void synchronize(){
        String json = "[{\"content\":\";l;ll;lk;lk\",\"conversationTime\":1526036458000,\"msgType\":\"1\",\"status\":3,\"token\":\"3a96fff95e6c4f41b930ffb48b688a75\",\"username\":\"wxid_zx2jsufjz9cf21\"},{\"content\":\"irtioroireuoi\",\"conversationTime\":1526036887000,\"msgType\":\"1\",\"status\":3,\"token\":\"3a96fff95e6c4f41b930ffb48b688a75\",\"username\":\"wxid_zx2jsufjz9cf21\"},{\"content\":\"dsfsf\",\"conversationTime\":1526036901000,\"msgType\":\"1\",\"status\":3,\"token\":\"3a96fff95e6c4f41b930ffb48b688a75\",\"username\":\"wxid_zx2jsufjz9cf21\"}]";

        List<WechatMessage> messages = GsonUtils.deserialize(json, new TypeToken<List<WechatMessage>>(){}.getType());
        wechatMessageService.synchronize(messages);
    }
}

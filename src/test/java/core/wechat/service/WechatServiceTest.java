package core.wechat.service;

import com.crm.core.wechat.entity.Wechat;
import com.crm.core.wechat.service.WechatService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml", "classpath:springbeans.xml"})
@ActiveProfiles(value = "test")
public class WechatServiceTest{

    @Autowired
    private WechatService wechatService;

    @Test
    public void save(){
        Wechat wechat = new Wechat();
        wechat.setWxno("hsl_test");
        wechat.setNickname("测试账号");

        wechatService.save(wechat);
    }

    @Test
    public void update(){
        Wechat wechat = new Wechat();
        wechat.setId("961fd3c5f8914c5796ae2c76cb057c2f");
        wechat.setWxno("lab969");
        wechat.setNickname("木木老师2");

        wechatService.update(wechat);
    }
}

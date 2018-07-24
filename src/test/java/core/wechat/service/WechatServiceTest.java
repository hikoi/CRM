package core.wechat.service;

import com.crm.core.wechat.consts.PurposeType;
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
        wechat.setCompanyId("93dc01bcd9474f71a57bfffb25d9b2cb");
        wechat.setNickname("宁&静");
        wechat.setWxno("ningjing4343");
        wechat.setType(PurposeType.DISTRIBUTE);

        wechatService.save(wechat);
    }
}

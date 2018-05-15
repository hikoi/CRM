package core.jpush.service;

import com.crm.core.jpush.service.JPushService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml"})
@ActiveProfiles(value = "test")
public class JPushServiceTest{

    @Autowired
    private JPushService jPushService;

    @Test
    public void push(){
        String wxno = "hsl_test";
    }
}

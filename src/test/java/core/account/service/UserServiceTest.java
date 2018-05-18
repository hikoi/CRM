package core.account.service;

import com.crm.core.account.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.wah.doraemon.entity.User;
import org.wah.doraemon.entity.consts.Sex;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml", "classpath:springbeans.xml"})
@ActiveProfiles(value = "test")
public class UserServiceTest{

    @Autowired
    private UserService userService;

    @Test
    public void save(){
        User user = new User();
        user.setAccountId("0");
        user.setNickname("超级管理员");
        user.setName("超级管理员");
        user.setSex(Sex.UNKNOWN);

        userService.save(user);
    }
}

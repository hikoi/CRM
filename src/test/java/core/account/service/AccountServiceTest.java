package core.account.service;

import com.crm.core.account.service.AccountService;
import com.crm.core.pem.dao.PemDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.wah.doraemon.entity.Account;
import org.wah.doraemon.utils.RSAUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml", "classpath:springbeans.xml"})
@ActiveProfiles(value = "test")
public class AccountServiceTest{

    @Autowired
    private AccountService accountService;

    @Autowired
    private PemDao pemDao;

    @Test
    public void register(){
        String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCaTWTPyqcqSApOO+DFKLYyNi6aF5/Tnl6Uw5avtodWOeLDTbhqjfX1Ji6duLuY6kqv09TiO8YEbRWnJH0KGPqlYDSY1agWZ6z4BVHHQNHfMsAjP1g4GiZKvHhAZegcOAO3J4Gt6BLEw/bsiNxQN720z55ULFeG2ILjLebhhJqP2QIDAQAB";
        String password = RSAUtils.encryptyPublicKey("crm888888", publicKey);

        accountService.register("root", password);
    }

    @Test
    public void login(){
//        String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCaTWTPyqcqSApOO+DFKLYyNi6aF5/Tnl6Uw5avtodWOeLDTbhqjfX1Ji6duLuY6kqv09TiO8YEbRWnJH0KGPqlYDSY1agWZ6z4BVHHQNHfMsAjP1g4GiZKvHhAZegcOAO3J4Gt6BLEw/bsiNxQN720z55ULFeG2ILjLebhhJqP2QIDAQAB";

        String publicKey = pemDao.getPublicKey();
        String password = RSAUtils.encryptyPublicKey("crm888888", publicKey);
        String username = "root";

        String password2 = "i2HAkK69l6MTmiAVoExXeFlTWFRho6AqxsVXkRsqAbIGE8OaqyQeWkDaJ+j1H9DKRXja1CalH2RdN9NcDddl7SsMlhAFC1hRlLN3AfcOUrr2hBXdnNh7hlVZ1D7JblINVOjAqY4fRrvYcvlZw6qfNj1c0Z2eDWIxtfFWyHagG+E=";
        System.out.println(password);
        System.out.println(password2);

        Account account = accountService.login(username, password);
        System.out.println(account.getId());
    }
}

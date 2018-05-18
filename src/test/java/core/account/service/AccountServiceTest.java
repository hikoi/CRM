package core.account.service;

import com.crm.core.account.service.AccountService;
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

    @Test
    public void register(){
        String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCaTWTPyqcqSApOO+DFKLYyNi6aF5/Tnl6Uw5avtodWOeLDTbhqjfX1Ji6duLuY6kqv09TiO8YEbRWnJH0KGPqlYDSY1agWZ6z4BVHHQNHfMsAjP1g4GiZKvHhAZegcOAO3J4Gt6BLEw/bsiNxQN720z55ULFeG2ILjLebhhJqP2QIDAQAB";
        String password = RSAUtils.encryptyPublicKey("crm888888", publicKey);

        Account account = new Account();
        account.setUsername("root");
        account.setPassword(password);

        accountService.register(account);
    }

    @Test
    public void login(){
        String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCaTWTPyqcqSApOO+DFKLYyNi6aF5/Tnl6Uw5avtodWOeLDTbhqjfX1Ji6duLuY6kqv09TiO8YEbRWnJH0KGPqlYDSY1agWZ6z4BVHHQNHfMsAjP1g4GiZKvHhAZegcOAO3J4Gt6BLEw/bsiNxQN720z55ULFeG2ILjLebhhJqP2QIDAQAB";
        String password = RSAUtils.encryptyPublicKey("crm888888", publicKey);
        String username = "root";

        Account account = accountService.login(username, password);
        System.out.println(account.getId());
    }
}

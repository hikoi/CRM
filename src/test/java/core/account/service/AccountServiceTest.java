package core.account.service;

import com.crm.core.account.service.AccountService;
import com.crm.core.pem.dao.PemDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.wah.doraemon.entity.consts.Sex;
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
    public void save(){
//        String companyId    = "93dc01bcd9474f71a57bfffb25d9b2cb";
//        String departmentId = "0008f7903b4443239223666f3c66a379";
//        String positionId   = "c5635b42e3e04f1a82a289f13d604838";
//        String username     = "13246308797";
//        String username     = "13750565823";
//        String password     = RSAUtils.encryptyPublicKey("moneycat888888", pemDao.getPublicKey());
//        String nickname     = "LnnWonXin";
//        String name         = "林文欣";
//        Sex    sex          = Sex.MALE;

//        accountService.save(username, password, nickname, name, sex, companyId, departmentId, positionId);

        String username = "root";
        String password = RSAUtils.encryptyPublicKey("moneycat2018", pemDao.getPublicKey());
        String nickname = "Administrator";
        String name     = "超级管理员";
        Sex    sex      = Sex.MALE;

        accountService.save(username, password, nickname, name, sex, null, null, null);
    }

    @Test
    public void login(){
        String username = "13246308797";
        String password = RSAUtils.encryptyPublicKey("moneycat2018", pemDao.getPublicKey());

        System.out.println(password);
//
//        String ticket = accountService.login(username, password);
//        System.out.println("=> " + ticket + " <=");
    }
}

package commons.utils;

import com.crm.core.pem.dao.PemDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.wah.doraemon.utils.RSAUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml", "classpath:springbeans.xml"})
@ActiveProfiles(value = "test")
public class RSAUtilsTest{

    @Autowired
    private PemDao pemDao;

    @Test
    public void test(){
        String publicKey  = pemDao.getPublicKey();
        String privateKey = pemDao.getPrivateKey();

        String password = "crm888888";

        String encode = RSAUtils.encryptyPublicKey(password, publicKey);
        System.out.println(encode);

        String encode2 = RSAUtils.encryptyPublicKey(password, publicKey);
        System.out.println(encode2);

        String decode = RSAUtils.decryptByPrivateKey(encode, privateKey);
        System.out.println(decode);

        boolean result = RSAUtils.equalsByPrivateKey(encode, encode2, privateKey);
        System.out.println(result);
    }
}

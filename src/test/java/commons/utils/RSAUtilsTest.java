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
        String privateKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAJGaKrcVEXJ+uUTms5CFNFXC2W+2lmVy2MTaGmRmEoksGHOui1vnx6aRYIFSxyzMqMHWgxIPd9CjxF6QDQiu9X44c3BUOphAx60XkkB5usZ8ElN4KGdJ7lhNIfSc9I0wI7NO4vbXhCSz04xjIdazAojsae3yGbbbUG672TbuOzgbAgMBAAECgYBLZ1+/uPV0KhSgjiloKtPTpjkdFZ4Fo9iG+by5SfNHfsKbBkW9rudCZ/9megwUM1kiKYlvjPsr3eQQZOGbIaCYmLCE7gPc4roMd1mcAwcA8qlwa8VVHKGWS8qNAtomxQRBrKZ/JcMYwaORzxq68rwMeGZKWUMsiQ/xGrxkI8NP4QJBANNj0MgSmlE3DKbNTuZi4+3LP8u65JM4+PX6RKNDZVlDF5fhhYhbKyg5XUY6ph1ev2NjtroCYvXI7VnmZcMVHRUCQQCwVDg5Jm5sa42DR1EfORSoNOQGSytiZAJ2zEN4YsoiheyoEdtrFNTxRpWse3bXa36OpTnJeSDQxKU8UGpnxCxvAkB9i9u05++E6qf17Ru+Et+CHGOAUThuA7lBBel7+YwYzpMVnlhbWqIwGpm+GxkLenk6wz8EDGTPywzEhuTLTx/dAkEArnAC1vbgEr4r1w18gUj02JrKYjeHCeUqUuAsdzI0Cgc3YeWYjcykBLTxaRsTLHWZVAC8tEXyYtXHKW3cdh8qXQJBAJ3xsa/x03WIld7A32+UkJTDceMCSyZX0wlwQla+dZGr20bs19kq8k4tnJxf4/ysoAPh8yMOWRhJijxKgnSizs4=";
        String pubilcKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCRmiq3FRFyfrlE5rOQhTRVwtlvtpZlctjE2hpkZhKJLBhzrotb58emkWCBUscszKjB1oMSD3fQo8RekA0IrvV+OHNwVDqYQMetF5JAebrGfBJTeChnSe5YTSH0nPSNMCOzTuL214Qks9OMYyHWswKI7Gnt8hm221Buu9k27js4GwIDAQAB";

        String password = "crm888888";

        String encode_1 = RSAUtils.encryptyPublicKey(password, pubilcKey);
        String encode_2 = RSAUtils.encryptyPublicKey(password, pubilcKey);

        System.out.println(encode_1);
        System.out.println(encode_2);

        String decode_1 = RSAUtils.decryptByPrivateKey(encode_1, privateKey);
        String decode_2 = RSAUtils.decryptByPrivateKey(encode_2, privateKey);

        System.out.println(decode_1);
        System.out.println(decode_2);

        System.out.println(RSAUtils.equalsByPrivateKey(encode_1, encode_2, privateKey));
    }
}

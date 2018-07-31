package core.permission.dao;

import com.crm.core.permission.dao.AccountRoleDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml", "classpath:springbeans.xml"})
@ActiveProfiles(value = "test")
public class AccountRoleDaoTest{

    @Autowired
    private AccountRoleDao accountRoleDao;

    @Test
    public void save(){
        String accountId = "0e9eb5cc9d254c0ba6689f8c20b432e5";
        String roleId    = "8b5838b2c794449483146df0d5884056";

        accountRoleDao.save(accountId, roleId);
    }
}

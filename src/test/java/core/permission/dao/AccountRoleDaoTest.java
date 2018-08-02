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
        String accountId = "4d4b0daf4def4daa820c3dabcd0fe507";
        String roleId    = "b984e1d67d744602b8db0eaf4bd5f9be";

        accountRoleDao.save(accountId, roleId);
    }
}

package core.sensitive.dao;

import com.crm.core.sensitive.dao.SensitiveWordDao;
import com.crm.core.sensitive.entity.SensitiveWord;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.wah.doraemon.entity.consts.UsingState;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml"})
@ActiveProfiles(value = "test")
public class SensitiveWordDaoTest{

    @Autowired
    private SensitiveWordDao sensitiveWordDao;

    @Test
    public void save(){
        SensitiveWord sensitive_Word_1 = new SensitiveWord();
        sensitive_Word_1.setContent("操");
        sensitive_Word_1.setState(UsingState.USABLE);
        sensitiveWordDao.saveOrUpdate(sensitive_Word_1);

        SensitiveWord sensitive_Word_2 = new SensitiveWord();
        sensitive_Word_2.setContent("尼玛");
        sensitive_Word_2.setState(UsingState.USABLE);
        sensitiveWordDao.saveOrUpdate(sensitive_Word_2);

        SensitiveWord sensitive_Word_3 = new SensitiveWord();
        sensitive_Word_3.setContent("我靠");
        sensitive_Word_3.setState(UsingState.USABLE);
        sensitiveWordDao.saveOrUpdate(sensitive_Word_3);
    }
}

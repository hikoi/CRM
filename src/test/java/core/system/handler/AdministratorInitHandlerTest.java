package core.system.handler;

import com.crm.core.system.handler.AdministratorInitHandler;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml", "classpath:springbeans.xml"})
@ActiveProfiles(value = "test")
public class AdministratorInitHandlerTest{

    @Autowired
    private AdministratorInitHandler administratorInitHandler;

    @Test
    public void init(){
        administratorInitHandler.init();
    }
}

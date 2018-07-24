package core.groups.service;

import com.crm.core.group.service.GroupsSellerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml", "classpath:springbeans.xml"})
@ActiveProfiles(value = "test")
public class GroupsSellerServiceTest{

    @Autowired
    private GroupsSellerService groupsSellerService;

    @Test
    public void save(){
        String sellerId = "9d1b86f5fdaf459b9dc773d8ef4663f1";
        String groupsId = "091b76638a134a16aca69eb7f1e19437";

        groupsSellerService.save(groupsId, sellerId);
    }
}

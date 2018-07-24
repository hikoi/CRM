package core.groups.service;

import com.crm.core.group.consts.GroupType;
import com.crm.core.group.entity.Groups;
import com.crm.core.group.service.GroupsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml", "classpath:springbeans.xml"})
@ActiveProfiles(value = "test")
public class GroupsServiceTest{

    @Autowired
    private GroupsService groupsService;

    @Test
    public void save(){
        String companyId = "93dc01bcd9474f71a57bfffb25d9b2cb";

        Groups groups = new Groups();
        groups.setIsDefault(true);
        groups.setName("销售小组");
        groups.setType(GroupType.SELLER);
        groups.setCompanyId(companyId);
        groupsService.save(groups);
    }
}

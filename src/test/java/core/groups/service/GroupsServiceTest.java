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

        Groups groups1 = new Groups();
        groups1.setIsDefault(false);
        groups1.setName("微信管理");
        groups1.setType(GroupType.MENU);
        groups1.setCompanyId(companyId);
        groupsService.save(groups1);

        Groups groups2 = new Groups();
        groups2.setIsDefault(false);
        groups2.setName("客服管理");
        groups2.setType(GroupType.MENU);
        groups2.setCompanyId(companyId);
        groupsService.save(groups2);

        Groups groups3 = new Groups();
        groups3.setIsDefault(false);
        groups3.setName("设备管理");
        groups3.setType(GroupType.MENU);
        groups3.setCompanyId(companyId);
        groupsService.save(groups3);

        Groups groups4 = new Groups();
        groups4.setIsDefault(false);
        groups4.setName("微信风控");
        groups4.setType(GroupType.MENU);
        groups4.setCompanyId(companyId);
        groupsService.save(groups4);

        Groups groups5 = new Groups();
        groups5.setIsDefault(false);
        groups5.setName("用户管理");
        groups5.setType(GroupType.MENU);
        groups5.setCompanyId(companyId);
        groupsService.save(groups5);

        Groups groups6 = new Groups();
        groups6.setIsDefault(false);
        groups6.setName("辅助营销");
        groups6.setType(GroupType.MENU);
        groups6.setCompanyId(companyId);
        groupsService.save(groups6);

        Groups groups7 = new Groups();
        groups7.setIsDefault(false);
        groups7.setName("其他设置");
        groups7.setType(GroupType.MENU);
        groups7.setCompanyId(companyId);
        groupsService.save(groups7);
    }
}

package core.groups.service;

import com.crm.core.group.service.GroupsWechatService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml", "classpath:springbeans.xml"})
@ActiveProfiles(value = "test")
public class GroupsWechatTest{

    @Autowired
    private GroupsWechatService groupsWechatService;

    @Test
    public void save(){
        String wechatId = "70faf75c6b174d32ba258686dfd8ffb7";
        String groupsId = "6e1ea310b32f48f89bfea1835a6fc126";

        groupsWechatService.save(groupsId, wechatId);
    }
}

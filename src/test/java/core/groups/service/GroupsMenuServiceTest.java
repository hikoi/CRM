package core.groups.service;

import com.crm.core.group.service.GroupsMenuService;
import com.crm.core.permission.entity.Menu;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml", "classpath:springbeans.xml"})
@ActiveProfiles(value = "test")
public class GroupsMenuServiceTest{

    @Autowired
    private GroupsMenuService groupsMenuService;

    @Test
    public void saveList(){
        //微信模块
        String groupsId = "61dbc5ee5aac48e4a965efb14f409e86";

        Menu menu1 = new Menu();
        menu1.setName("微信列表");
        menu1.setUrl("wechatList");

        Menu menu2 = new Menu();
        menu2.setName("微信分组");
        menu2.setUrl("wechatGroup");
    }
}

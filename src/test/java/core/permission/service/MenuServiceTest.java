package core.permission.service;

import com.crm.core.permission.entity.Menu;
import com.crm.core.permission.service.MenuService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml", "classpath:springbeans.xml"})
@ActiveProfiles(value = "test")
public class MenuServiceTest{

    @Autowired
    private MenuService menuService;

    @Test
    public void saveList(){
        //微信管理
        String wechatGroupsId = "1961f22f69644c06a29b94c6c7dff710";

        Menu menu1 = new Menu();
        menu1.setName("微信列表");
        menu1.setUrl("wechatList");

        Menu menu2 = new Menu();
        menu2.setName("微信分组");
        menu2.setUrl("wechatGrous");

        menuService.saveList(wechatGroupsId, Arrays.asList(menu1, menu2));

        //客服管理
        String serviceGroupsId = "006793c5d37449bfa4c2e0caf752d0a8";

        Menu menu3 = new Menu();
        menu3.setName("好友列表");
        menu3.setUrl("friendsList");

        Menu menu4 = new Menu();
        menu4.setName("好友分组");
        menu4.setUrl("friendGrous");

        Menu menu5 = new Menu();
        menu5.setName("分配规则");
        menu5.setUrl("allocation");

        Menu menu6 = new Menu();
        menu6.setName("分配记录");
        menu6.setUrl("distribution");

        menuService.saveList(serviceGroupsId, Arrays.asList(menu3, menu4, menu5, menu6));

        //设备管理
        String deviceGroupsId = "d57e8222138e4ced8f00fcd222a8d64f";

        Menu menu7 = new Menu();
        menu7.setName("设备列表");
        menu7.setUrl("equipmentList");

        Menu menu8 = new Menu();
        menu8.setName("设备分组");
        menu8.setUrl("equipmentGrous");

        menuService.saveList(deviceGroupsId, Arrays.asList(menu7, menu8));

        //微信风控
        String wechatRiskGroupsId = "fe67e36358924821a8ed61030bc07df3";

        Menu menu9 = new Menu();
        menu9.setName("敏感词设置");
        menu9.setUrl("sensitiveSet");

        Menu menu10 = new Menu();
        menu10.setName("敏感词操作");
        menu10.setUrl("sensitiveOperations");

        Menu menu11 = new Menu();
        menu11.setName("重复好友统计");
        menu11.setUrl("repeatFriends");

        Menu menu12 = new Menu();
        menu12.setName("微信财务统计");
        menu12.setUrl("wechatFinancial");

        menuService.saveList(wechatRiskGroupsId, Arrays.asList(menu9, menu10, menu11, menu12));

        //用户管理
        String userGroupsId = "dab75db0adab4dc1b66b70e191c746b0";

        Menu menu13 = new Menu();
        menu13.setName("部门管理");
        menu13.setUrl("department");

        Menu menu14 = new Menu();
        menu14.setName("账户管理");
        menu14.setUrl("account");

        menuService.saveList(userGroupsId, Arrays.asList(menu13, menu14));

        //辅助营销
        String sellWayGroupsId = "bb181b81ae824d38af0faf4068545c7f";

        Menu menu15 = new Menu();
        menu15.setName("发朋友圈");
        menu15.setUrl("hairFriends");

        Menu menu16 = new Menu();
        menu16.setName("浅度养号");
        menu16.setUrl("shallowRaise");

        Menu menu17 = new Menu();
        menu17.setName("养号操作记录");
        menu17.setUrl("recordNumber");

        menuService.saveList(sellWayGroupsId, Arrays.asList(menu15, menu16, menu17));

        //其他设置
        String orderSettingGroupsId = "23d2c7d3f33743d2bc828ffd734fa889";

        Menu menu18 = new Menu();
        menu18.setName("更新日志");
        menu18.setUrl("updateLog");

        Menu menu19 = new Menu();
        menu19.setName("操作记录");
        menu19.setUrl("operationRecords");

        menuService.saveList(orderSettingGroupsId, Arrays.asList(menu18, menu19));
    }

    @Test
    public void findByAccountId(){
//        String accountId = "0e9eb5cc9d254c0ba6689f8c20b432e5";
        String accountId = "621c62f470e94160a4f9417fe82966b2";

        List<Menu> list = menuService.findByAccountId(accountId);
        System.out.println(list.size());
    }
}

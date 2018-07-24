package core.groups.dao;

import com.crm.core.group.dao.GroupsWechatFriendDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml", "classpath:springbeans.xml"})
@ActiveProfiles(value = "test")
public class GroupsWechatFriendDaoTest{

    @Autowired
    private GroupsWechatFriendDao groupsWechatFriendDao;

    @Test
    public void saveList(){
        String defaultGroupsId   = "0";
        String intentionGroupsId = "1";
        String dontTalkGroupsId  = "2";
        String blackListGroupsId = "3";

        List<String> defaultFriendIds = new ArrayList<String>(Arrays.asList("00055b446afb4e659e710ff0845d77db",
                                                                            "6fb4d33865604b048816367057ae75e0",
                                                                            "8673774b684d4e848dfa2c041183bf12",
                                                                            "8e801236a0f04dfda85fe075dcc2b38f"));

        List<String> intentionFriendIds = new ArrayList<String>(Arrays.asList("020df3462f2f4d36a7cbdbef9f6e4384",
                                                                              "0c7c0777848c4c75aaec6e2b0700512d"));

        List<String> dontTalkFriendIds = new ArrayList<String>(Arrays.asList("18dd3c451c374128a78978c898e2048f",
                                                                             "196ec98e631648a4a704e0c41551bd91"));

        List<String> blackListFriendIds = new ArrayList<String>(Arrays.asList("327e9196a6714d7aa4cb1348ce518643",
                                                                              "66f00ec0e3034308b34bf4033a9a669e"));

        groupsWechatFriendDao.saveList(defaultGroupsId, defaultFriendIds);
        groupsWechatFriendDao.saveList(intentionGroupsId, intentionFriendIds);
        groupsWechatFriendDao.saveList(dontTalkGroupsId, dontTalkFriendIds);
        groupsWechatFriendDao.saveList(blackListGroupsId, blackListFriendIds);
    }
}

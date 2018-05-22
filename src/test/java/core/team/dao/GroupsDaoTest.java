package core.team.dao;

import com.crm.core.group.dao.GroupsDao;
import com.crm.core.group.entity.Groups;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.wah.doraemon.entity.consts.UsingState;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml"})
@ActiveProfiles(value = "test")
public class GroupsDaoTest{

    @Autowired
    private GroupsDao groupsDao;

    @Test
    public void save(){
        Groups group_1 = new Groups();
        group_1.setName("舒敏");
        group_1.setState(UsingState.USABLE);
        groupsDao.saveOrUpdate(group_1);

        Groups group_2 = new Groups();
        group_2.setName("丰胸");
        group_2.setState(UsingState.USABLE);
        groupsDao.saveOrUpdate(group_2);
    }

    @Test
    public void updateRelationByGroupId(){
        String groupId = "24754195a7a54aa88a671156f3c91ab8";
        List<String> wechatIds = new ArrayList<String>(Arrays.asList("3a96fff95e6c4f41b930ffb48b688a75"));

        groupsDao.updateRelationByGroupId(groupId, wechatIds);
    }

    @Test
    public void updateRelationByWechatId(){
        String wechatId = "3a96fff95e6c4f41b930ffb48b688a75";
        List<String> groupIds = new ArrayList<String>(Arrays.asList("fffeaa1420ec4f848847a1d0c6bf2209", "24754195a7a54aa88a671156f3c91ab8"));

        groupsDao.updateRelationByWechatId(wechatId, groupIds);
    }
}

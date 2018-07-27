package core.groups.dao;

import com.crm.core.group.dao.GroupsWordDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml", "classpath:springbeans.xml"})
@ActiveProfiles(value = "test")
public class GroupsWordDaoTest{

    @Autowired
    private GroupsWordDao groupsWordDao;

    @Test
    public void save(){
        String wordId   = "04d8027b09414c8a9a0b9c62202c8e06";
        String groupsId = "6e1ea310b32f48f89bfea1835a6fc126";

        groupsWordDao.save(groupsId, wordId);
    }
}

package core.permission.service;

import com.crm.core.permission.entity.Role;
import com.crm.core.permission.service.RoleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml", "classpath:springbeans.xml"})
@ActiveProfiles(value = "test")
public class RoleServiceTest{

    @Autowired
    private RoleService roleService;

    @Test
    public void save(){
        Role role = new Role();
        role.setName("企业管理员");

        roleService.save(role);
    }
}

package core.account.service;

import com.crm.core.account.entity.Permission;
import com.crm.core.account.service.PermissionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml"})
@ActiveProfiles(value = "test")
public class PermissionServiceTest{

    @Autowired
    private PermissionService permissionService;

    @Test
    public void save(){
        Permission permission = new Permission();
        permission.setUrl("/**");
        permission.setDescription("超级管理员权限");

        permissionService.save(permission);
    }
}

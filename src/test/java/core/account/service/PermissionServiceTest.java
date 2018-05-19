package core.account.service;

import com.crm.core.account.entity.Permission;
import com.crm.core.account.service.PermissionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml", "classpath:springbeans.xml"})
@ActiveProfiles(value = "test")
public class PermissionServiceTest{

    @Autowired
    private PermissionService permissionService;

    @Test
    public void save(){
        Permission permission = new Permission();
        permission.setUrl("/**");
        permission.setDescription("超级管理员权限");
        permission.setMethod(RequestMethod.DELETE);

        permissionService.save(permission);
    }

    @Test
    public void updateRelationByAccountId(){
        String accountId = "0";
        List<String> permissionIds = new ArrayList<String>(Arrays.asList("0"));

        permissionService.updateRelationByAccountId(accountId, permissionIds);
    }

    @Test
    public void findByAccountId(){
        String accountId = "0";

        Set<Permission> permissions = permissionService.findByAccountId(accountId);
        System.out.println(permissions.size());
    }

    @Test
    public void synchronize(){
        permissionService.synchronize();
    }
}

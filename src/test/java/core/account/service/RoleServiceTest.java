package core.account.service;

import com.crm.core.account.entity.Role;
import com.crm.core.account.service.RoleService;
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
public class RoleServiceTest{

    @Autowired
    private RoleService roleService;

    @Test
    public void save(){
        Role role = new Role();
        role.setName("超级管理员");

        roleService.save(role);
    }

    @Test
    public void updateRelationByRoleId(){
        String roleId = "0";
        List<String> permissionIds = new ArrayList<String>(Arrays.asList("0"));

        roleService.updateRelationByRoleId(roleId, permissionIds);
    }

    @Test
    public void updateRelationByAccountId(){
        String accountId = "0";
        List<String> roleIds = new ArrayList<String>(Arrays.asList("0"));

        roleService.updateRelationByAccountId(accountId, roleIds);
    }
}

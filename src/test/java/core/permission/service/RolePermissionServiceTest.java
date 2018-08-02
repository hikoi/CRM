package core.permission.service;

import com.crm.core.permission.consts.ResourceType;
import com.crm.core.permission.dao.PermissionDao;
import com.crm.core.permission.entity.Permission;
import com.crm.core.permission.service.RolePermissionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.wah.doraemon.utils.ObjectUtils;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml", "classpath:springbeans.xml"})
@ActiveProfiles(value = "test")
public class RolePermissionServiceTest{

    @Autowired
    private RolePermissionService rolePermissionService;

    @Autowired
    private PermissionDao permissionDao;

    @Test
    public void saveList(){
        String roleId = "b984e1d67d744602b8db0eaf4bd5f9be";

        List<String> permissionIds = Arrays.asList("6a8f2cb2275d42888b9ec9d24f7648a9");

        rolePermissionService.saveList(roleId, permissionIds);

//        List<Permission> permissions = permissionDao.findByTypes(Arrays.asList(ResourceType.FUNCTION, ResourceType.MENU));
//        rolePermissionService.saveList(roleId, ObjectUtils.ids(permissions));
    }
}

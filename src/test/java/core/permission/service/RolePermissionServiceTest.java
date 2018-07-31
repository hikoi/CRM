package core.permission.service;

import com.crm.core.permission.service.RolePermissionService;
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
public class RolePermissionServiceTest{

    @Autowired
    private RolePermissionService rolePermissionService;

    @Test
    public void saveList(){
        String roleId = "8b5838b2c794449483146df0d5884056";

        List<String> permissionIds = Arrays.asList("28d19579dcad42198ce2b3d8a5f748d3",
                                                   "318c32396a114136b6fdd589aec61438",
                                                   "37ffc064d5e542c1825d8c4cab22cecc",
                                                   "4b5b83d9c5284264bde7222723744c56",
                                                   "4c88100783b34c06a67b0c71ee9ad3dc",
                                                   "5367183c150f4c3f959624341be7669c",
                                                   "70a489f2e2a549e99743aae93b8b741d",
                                                   "92f70b5ff51c4faa85aa2ba619db5a58",
                                                   "959634c7d5dd4aefbd815693dc2abdd5",
                                                   "9f8b692b0fcd467e8147785a9024c072",
                                                   "a03781775fb04102ba80d1991d7f0537",
                                                   "a47a17246ef64cc093a2f5508c5af97b",
                                                   "af880f3fc2a84769b2df58f7e56d3d87",
                                                   "b322405c82424c1494f0be3cc6e5a629",
                                                   "bbf6d8146e7b4963b240c6a5fb63e562",
                                                   "be76d996976f4711a4472d0233df5098",
                                                   "c2981ed3f6144fc990188def25e40828",
                                                   "de4714cebdf54a3d9c07c7d25baa410d",
                                                   "e2b42f1040fd4010b1cc6d25155f1873");

        rolePermissionService.saveList(roleId, permissionIds);
    }
}

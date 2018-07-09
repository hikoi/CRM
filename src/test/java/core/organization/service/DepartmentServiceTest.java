package core.organization.service;

import com.crm.core.organization.entity.Department;
import com.crm.core.organization.service.DepartmentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml", "classpath:springbeans.xml"})
@ActiveProfiles(value = "test")
public class DepartmentServiceTest{

    @Autowired
    private DepartmentService departmentService;

    @Test
    public void save(){
        String companyId = "93dc01bcd9474f71a57bfffb25d9b2cb";

        Department department = new Department();
        department.setCompanyId(companyId);
        department.setName("技术部");
        departmentService.save(department);
    }

    @Test
    public void find(){
        String companyId = "93dc01bcd9474f71a57bfffb25d9b2cb";

        List<Department> list = departmentService.find(null, companyId);
        System.out.println("=> " + list.size() + " <=");
    }
}

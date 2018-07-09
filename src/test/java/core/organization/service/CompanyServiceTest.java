package core.organization.service;

import com.crm.core.organization.entity.Company;
import com.crm.core.organization.service.CompanyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml", "classpath:springbeans.xml"})
@ActiveProfiles(value = "test")
public class CompanyServiceTest{

    @Autowired
    private CompanyService companyService;

    @Test
    public void save(){
        Company company = new Company();
        company.setName("聚财猫科技有限公司");
        companyService.save(company);
    }

    @Test
    public void getById(){
        String id = "93dc01bcd9474f71a57bfffb25d9b2cb";

        Company company = companyService.getById(id);
        System.out.println("=> " + company.getId() + " <=");
    }
}

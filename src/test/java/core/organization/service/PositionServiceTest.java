package core.organization.service;

import com.crm.core.organization.entity.Position;
import com.crm.core.organization.service.PositionService;
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
public class PositionServiceTest{

    @Autowired
    private PositionService positionService;

    @Test
    public void save(){
        String departmentId = "0008f7903b4443239223666f3c66a379";

        Position position = new Position();
        position.setDepartmentId(departmentId);
        position.setName("Java工程师");
        positionService.save(position);
    }

    @Test
    public void find(){
        String departmentId = "0008f7903b4443239223666f3c66a379";

        List<Position> list = positionService.find(null, departmentId);
        System.out.println("=> " + list.size() + " <=");
    }
}

package core.sensitive.service;

import com.crm.core.sensitive.service.SensitiveWordService;
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
public class SensitiveWordServiceTest{

    @Autowired
    private SensitiveWordService sensitiveWordService;

    @Test
    public void updateRelationByGroupId(){
        String groupId = "20bde4079df4464e9198f11cc7b07420";
        List<String> sensitiveIds = new ArrayList<String>(Arrays.asList("822c68d305c2451dbe8cb4086c7efe35", "85c131dd7dfc4e608281d6e918d66d2d"));

        sensitiveWordService.updateRelationByGroupId(groupId, sensitiveIds);
    }

    @Test
    public void updateRelationByWechatId(){
        String wechatId = "3a96fff95e6c4f41b930ffb48b688a75";
        List<String> sensitiveIds = new ArrayList<String>(Arrays.asList("822c68d305c2451dbe8cb4086c7efe35", "85c131dd7dfc4e608281d6e918d66d2d"));

        sensitiveWordService.updateRelationByWechatId(wechatId, sensitiveIds);

        while(true){

        }
    }

    @Test
    public void getRegEx(){
        String wxno = "hsl_test";

        System.out.println(sensitiveWordService.getRegEx(wxno));
    }
}

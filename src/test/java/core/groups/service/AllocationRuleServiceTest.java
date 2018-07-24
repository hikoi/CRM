package core.groups.service;

import com.crm.core.group.consts.AllocationType;
import com.crm.core.group.entity.AllocationRule;
import com.crm.core.group.service.AllocationRuleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.wah.doraemon.entity.consts.UsingState;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml", "classpath:springbeans.xml"})
@ActiveProfiles(value = "test")
public class AllocationRuleServiceTest{

    @Autowired
    private AllocationRuleService allocationRuleService;

    @Test
    public void save(){
        List<String> wechatGroups = new ArrayList<String>(Arrays.asList("6e1ea310b32f48f89bfea1835a6fc126"));
        List<String> regions      = new ArrayList<String>(Arrays.asList("091b76638a134a16aca69eb7f1e19437"));

        AllocationRule rule = new AllocationRule();
        rule.setState(UsingState.USABLE);
        rule.setType(AllocationType.BY_GROUP);
        rule.setName("规则一");
        rule.setWechatGroups(wechatGroups);
        rule.setRegions(regions);
        rule.setOnlineOnly(false);

        allocationRuleService.save(rule);
    }
}

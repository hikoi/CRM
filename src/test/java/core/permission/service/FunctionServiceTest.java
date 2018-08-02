package core.permission.service;

import com.crm.commons.utils.ScanUtils;
import com.crm.core.permission.entity.Function;
import com.crm.core.permission.service.FunctionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml", "classpath:springbeans.xml"})
@ActiveProfiles(value = "test")
public class FunctionServiceTest{

    @Autowired
    private FunctionService functionService;

    @Test
    public void saveList(){
        //现有功能
        List<Function> functions = new ArrayList<Function>();

        List<Class> classes = ScanUtils.findBeans("com.crm.**.webservice", Arrays.asList(RestController.class));
        if(classes != null && !classes.isEmpty()){
            //遍历
            for(Class clazz : classes){
                RequestMapping mapping = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
                String[] urls = mapping.value();

                for(Method method : clazz.getDeclaredMethods()){
                    RequestMapping methodMapping = (RequestMapping) method.getAnnotation(RequestMapping.class);
                    String[] methodUrls = methodMapping.value();
                    RequestMethod[] methods = methodMapping.method();

                    for(String url : urls){
                        for(String methodUrl : methodUrls){
                            for(RequestMethod requestMethod : methods){
                                Function function = new Function();
                                function.setUrl(url + methodUrl);
                                function.setMethod(requestMethod);

                                functions.add(function);
                            }
                        }
                    }
                }
            }
        }

        functionService.saveList(functions);
    }
}

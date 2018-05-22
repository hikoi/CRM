package core.permission.service;

import com.crm.commons.consts.Constants;
import com.crm.commons.utils.ScanUtils;
import com.crm.core.permission.consts.ResourceType;
import com.crm.core.permission.dao.FunctionDao;
import com.crm.core.permission.dao.PermissionDao;
import com.crm.core.permission.entity.Function;
import com.crm.core.permission.entity.Permission;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.wah.doraemon.utils.IDGenerator;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml", "classpath:springbeans.xml"})
@ActiveProfiles(value = "test")
public class FunctionServiceTest{

    @Autowired
    private FunctionDao functionDao;

    @Autowired
    private PermissionDao permissionDao;

    @Test
    public void init(){
        List<Function> insert      = new ArrayList<Function>();
        List<Function>   update      = new ArrayList<Function>();
        List<Permission> permissions = new ArrayList<Permission>();

        //原有功能
        List<Function> original = functionDao.find(null, null, null);
        //现有功能
        List<Function> functions = new ArrayList<Function>();

        List<Class> classes = ScanUtils.findBeans(Constants.PROJECT_ROOT, Constants.API_ANNOTATIONS);
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

        for(Function function : functions){
            if(original.contains(function)){
                function.setId(original.get(original.indexOf(function)).getId());
                update.add(function);
            }else{
                function.setId(IDGenerator.uuid32());
                insert.add(function);

                Permission permission = new Permission();
                permission.setResourceId(function.getId());
                permission.setType(ResourceType.FUNCTION);
                permissions.add(permission);
            }
        }
        //新增
        if(!insert.isEmpty()){
            functionDao.saveList(insert);
            permissionDao.saveList(permissions);
        }
        //修改
        if(!update.isEmpty()){
            functionDao.updateList(update);
        }
    }
}

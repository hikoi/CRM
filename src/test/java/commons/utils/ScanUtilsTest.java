package commons.utils;

import com.crm.commons.utils.ScanUtils;
import com.crm.core.account.entity.Permission;
import org.junit.Test;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

public class ScanUtilsTest{

    @Test
    public void findBeans(){
        String basePackage = "com.crm";

        List<Class> classes = ScanUtils.findBeans(basePackage, null);
        System.out.println(classes.size());
    }

    @Test
    public void findBeansWithAnnotations(){
        String basePackage = "com.crm";

        List<Class<? extends Annotation>> annotations = new ArrayList<Class<? extends Annotation>>();
        annotations.add(Controller.class);
        annotations.add(RestController.class);

        List<Class> classes = ScanUtils.findBeans(basePackage, annotations);
        System.out.println(classes.size());
    }

    @Test
    public void findAnnotations(){
        String basePackage = "com.crm";

        List<Class<? extends Annotation>> annotations = new ArrayList<Class<? extends Annotation>>();
        annotations.add(Controller.class);
        annotations.add(RestController.class);

        List<Annotation> target = ScanUtils.findAnnotations(basePackage, annotations);
        System.out.println(target.size());
    }

    @Test
    public void findPermission(){
        String basePackage = "com.crm.core.device";

        List<Permission> permissions = ScanUtils.findPermission(basePackage);
        System.out.println(permissions.size());
    }
}

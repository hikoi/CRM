package core.permission.service;

import com.crm.commons.utils.ScanUtils;
import com.crm.core.permission.consts.ResourceType;
import com.crm.core.permission.dao.MenuDao;
import com.crm.core.permission.dao.PermissionDao;
import com.crm.core.permission.entity.Menu;
import com.crm.core.permission.entity.Permission;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.RequestMapping;
import org.wah.doraemon.utils.IDGenerator;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml", "classpath:springbeans.xml"})
@ActiveProfiles(value = "test")
public class MenuServiceTest{

    @Autowired
    private MenuDao menuDao;

    @Autowired
    private PermissionDao permissionDao;

    @Test
    public void init(){
        //页面类
        List<Class> classes = ScanUtils.findBeans("com.crm.core", Arrays.asList(Controller.class));
        //菜单
        List<Menu> menus = new ArrayList<Menu>();
        //资源
        List<Permission> permissions = new ArrayList<Permission>();

        for(Class clazz : classes){
            RequestMapping mapping = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
            String[] urls = mapping.value();

            for(String url : urls){
                Menu menu = new Menu();
                menu.setId(IDGenerator.uuid32());
                menus.add(menu);

                for(Method method : clazz.getDeclaredMethods()){
                    RequestMapping methodMapping = (RequestMapping) method.getAnnotation(RequestMapping.class);
                    String[] methodUrls = methodMapping.value();

                    for(String methodUrl : methodUrls){
                        Menu child = new Menu();
                        child.setId(IDGenerator.uuid32());
                        child.setUrl(url + methodUrl);
                        child.setParentId(menu.getId());
                        menus.add(child);
                    }
                }
            }

            for(Menu menu : menus){
                Permission permission = new Permission();
                permission.setResourceId(menu.getId());
                permission.setType(ResourceType.MENU);
                permissions.add(permission);
            }

            //保存菜单
            menuDao.saveList(menus);
            //保存权限
            permissionDao.saveList(permissions);
        }
    }
}

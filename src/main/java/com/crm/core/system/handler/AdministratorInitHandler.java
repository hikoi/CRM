package com.crm.core.system.handler;

import com.crm.core.account.dao.AccountDao;
import com.crm.core.account.dao.UserDao;
import com.crm.core.pem.dao.PemDao;
import com.crm.core.permission.consts.ResourceType;
import com.crm.core.permission.dao.FunctionDao;
import com.crm.core.permission.dao.MenuDao;
import com.crm.core.permission.dao.PermissionDao;
import com.crm.core.permission.dao.RoleDao;
import com.crm.core.permission.entity.Function;
import com.crm.core.permission.entity.Menu;
import com.crm.core.permission.entity.Permission;
import com.crm.core.permission.entity.Role;
import com.sun.xml.internal.bind.v2.model.core.ID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMethod;
import org.wah.doraemon.entity.Account;
import org.wah.doraemon.entity.User;
import org.wah.doraemon.entity.consts.Sex;
import org.wah.doraemon.utils.IDGenerator;
import org.wah.doraemon.utils.ObjectUtils;
import org.wah.doraemon.utils.RSAUtils;

import java.util.Arrays;

@Component
public class AdministratorInitHandler{

    @Autowired
    private PemDao pemDao;

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private FunctionDao functionDao;

    @Autowired
    private MenuDao menuDao;

    @Autowired
    private PermissionDao permissionDao;

    /**
     * 创建超级管理员账户
     * 创建超级管理员角色
     * 配置权限
     */
    @Transactional(readOnly = false)
    public void init(){
        //创建超级管理员账户
        String publicKey = pemDao.getPublicKey();

        Account account = new Account();
        account.setUsername("root");
        account.setPassword(RSAUtils.encryptyPublicKey("crm888888", publicKey));
        accountDao.saveOrUpdate(account);

        //创建超级管理员用户
        User user = new User();
        user.setName("超级管理员");
        user.setNickname("超级管理员");
        user.setAccountId(account.getId());
        user.setSex(Sex.UNKNOWN);
        userDao.saveOrUpdate(user);

        //创建超级管理员角色
        Role role = new Role();
        role.setName("administrator");
        roleDao.saveOrUpdate(role);

        //创建超级管理员功能
        Function post = new Function();
        post.setId(IDGenerator.uuid32());
        post.setNeedAllot(true);
        post.setMethod(RequestMethod.POST);
        post.setUrl("/**");
        post.setDescription("超级管理员POST请求");

        Function get = new Function();
        get.setId(IDGenerator.uuid32());
        get.setNeedAllot(true);
        get.setMethod(RequestMethod.GET);
        get.setUrl("/**");
        get.setDescription("超级管理员GET请求");

        Function put = new Function();
        put.setId(IDGenerator.uuid32());
        put.setNeedAllot(true);
        put.setMethod(RequestMethod.PUT);
        put.setUrl("/**");
        put.setDescription("超级管理员PUT请求");

        Function delete = new Function();
        delete.setId(IDGenerator.uuid32());
        delete.setNeedAllot(true);
        delete.setMethod(RequestMethod.DELETE);
        delete.setUrl("/**");
        delete.setDescription("超级管理员DELETE请求");
        functionDao.saveList(Arrays.asList(post, get, put, delete));

        //基础菜单
        Menu organizationMenu = new Menu();
        organizationMenu.setId(IDGenerator.uuid32());
        organizationMenu.setName("企业架构");

        Menu companyMenu = new Menu();
        companyMenu.setId(IDGenerator.uuid32());
        companyMenu.setName("公司管理");
        companyMenu.setParentId(organizationMenu.getId());
        companyMenu.setUrl("/page/backstage/company/index");

        Menu permissionMenu = new Menu();
        permissionMenu.setId(IDGenerator.uuid32());
        permissionMenu.setName("权限配置");

        Menu roleMenu = new Menu();
        roleMenu.setId(IDGenerator.uuid32());
        roleMenu.setName("角色管理");
        roleMenu.setParentId(permissionMenu.getId());
        roleMenu.setUrl("/page/backstage/role/index");

        Menu functionMenu = new Menu();
        functionMenu.setId(IDGenerator.uuid32());
        functionMenu.setName("功能管理");
        functionMenu.setParentId(permissionMenu.getId());
        functionMenu.setUrl("/page/backstage/function/index");

        Menu menuMenu = new Menu();
        menuMenu.setId(IDGenerator.uuid32());
        menuMenu.setName("菜单管理");
        menuMenu.setParentId(permissionMenu.getId());
        menuMenu.setUrl("/page/backstage/menu/index");

        Menu accountMenu = new Menu();
        accountMenu.setId(IDGenerator.uuid32());
        accountMenu.setName("账户管理");

        Menu accountSecondMenu = new Menu();
        accountSecondMenu.setId(IDGenerator.uuid32());
        accountSecondMenu.setName("账户管理");
        accountSecondMenu.setParentId(accountMenu.getId());
        accountSecondMenu.setUrl("/page/backstage/account/index");
        menuDao.saveList(Arrays.asList(organizationMenu, companyMenu, permissionMenu, roleMenu, functionMenu, menuMenu,
                                       accountMenu, accountSecondMenu));

        //创建超级管理员权限
        Permission postPermission = new Permission();
        postPermission.setType(ResourceType.FUNCTION);
        postPermission.setResourceId(post.getId());

        Permission getPermission = new Permission();
        getPermission.setType(ResourceType.FUNCTION);
        getPermission.setResourceId(get.getId());

        Permission putPermission = new Permission();
        putPermission.setType(ResourceType.FUNCTION);
        putPermission.setResourceId(put.getId());

        Permission deletePermission = new Permission();
        deletePermission.setType(ResourceType.FUNCTION);
        deletePermission.setResourceId(delete.getId());

        Permission organizationMenuPermission = new Permission();
        organizationMenuPermission.setType(ResourceType.MENU);
        organizationMenuPermission.setResourceId(organizationMenu.getId());

        Permission companyMenuPermission = new Permission();
        companyMenuPermission.setType(ResourceType.MENU);
        companyMenuPermission.setResourceId(companyMenu.getId());

        Permission permissionMenuPermission = new Permission();
        permissionMenuPermission.setType(ResourceType.MENU);
        permissionMenuPermission.setResourceId(permissionMenu.getId());

        Permission roleMenuPermission = new Permission();
        roleMenuPermission.setType(ResourceType.MENU);
        roleMenuPermission.setResourceId(roleMenu.getId());

        Permission functionMenuPermission = new Permission();
        functionMenuPermission.setType(ResourceType.MENU);
        functionMenuPermission.setResourceId(functionMenu.getId());

        Permission menuMenuPermission = new Permission();
        menuMenuPermission.setType(ResourceType.MENU);
        menuMenuPermission.setResourceId(menuMenu.getId());

        Permission accountMenuPermission = new Permission();
        accountMenuPermission.setType(ResourceType.MENU);
        accountMenuPermission.setResourceId(accountMenu.getId());

        Permission accountSecondMenuPermission = new Permission();
        accountSecondMenuPermission.setType(ResourceType.MENU);
        accountSecondMenuPermission.setResourceId(accountSecondMenu.getId());

        permissionDao.saveList(Arrays.asList(postPermission, getPermission, putPermission, deletePermission,
                                             organizationMenuPermission, companyMenuPermission, permissionMenuPermission,
                                             roleMenuPermission, functionMenuPermission, menuMenuPermission,
                                             accountMenuPermission, accountSecondMenuPermission));

        //角色配置权限
        permissionDao.updateFunctionsToRole(ObjectUtils.ids(Arrays.asList(postPermission, getPermission, putPermission, deletePermission)), role.getId());
        permissionDao.updateMenusToRole(ObjectUtils.ids(Arrays.asList(organizationMenuPermission, companyMenuPermission,
                                                                      permissionMenuPermission, roleMenuPermission,
                                                                      functionMenuPermission, menuMenuPermission,
                                                                      accountMenuPermission, accountSecondMenuPermission)), role.getId());
        //账户配置角色
        roleDao.updateRelationsToAccount(Arrays.asList(role.getId()), account.getId());
    }
}

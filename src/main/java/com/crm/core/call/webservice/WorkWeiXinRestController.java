package com.crm.core.call.webservice;

import com.crm.core.call.utils.SimpleUserList;
import com.crm.core.call.utils.WorkWeiXinUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Created by miku03 on 2018/5/11.
 */
@RestController
@RequestMapping(value = "/api/1.0/workWx")
public class WorkWeiXinRestController {

    private static Logger logger = LoggerFactory.getLogger(WorkWeiXinRestController.class);


    @RequestMapping(value = "/getMyUserInfo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getMyUserInfo(HttpServletRequest request, HttpServletResponse response) {
        String code = request.getParameter("code");
        String access_token = WorkWeiXinUtil.geToken();
        String userId = WorkWeiXinUtil.getUserId(code, access_token);
        String userInfo = WorkWeiXinUtil.getUserInfo(userId, access_token);
        return userInfo;
    }

    @RequestMapping(value = "/getUserListByUserName", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getUserListByUserName(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        System.out.println("name: " + name);
        String access_token = WorkWeiXinUtil.geToken();
//        客服部id:2
        String dpStr = WorkWeiXinUtil.getDepartmentList("2", access_token);
        System.out.println("dpStr: " + dpStr);
        Gson gson = new Gson();
        java.lang.reflect.Type type = new TypeToken<SimpleUserList>() {
        }.getType();
        SimpleUserList simpleUserList = gson.fromJson(dpStr, type);

        String advisor = name.replace("老师", "");
        System.out.println("advisor: " + advisor);
        String myDpId = "";
        for (int i = 0; i < simpleUserList.department.size(); i++) {
            System.out.println("test.department.get(i).name: " + simpleUserList.department.get(i).name);
            if (simpleUserList.department.get(i).name.contains(advisor)) {
                myDpId = simpleUserList.department.get(i).id;
            }
        }
        System.out.println("myDpId: " + myDpId);
        WorkWeiXinUtil.getUserList(myDpId, access_token);
        return WorkWeiXinUtil.getUserList(myDpId, access_token);
    }

    @RequestMapping(value = "/getClientUserInfo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getClientUserInfo(HttpServletRequest request, HttpServletResponse response) {
        String userId = request.getParameter("userId");
        String access_token = WorkWeiXinUtil.geToken();
        String userInfo = WorkWeiXinUtil.getUserInfo(userId, access_token);
        return userInfo;
    }
}

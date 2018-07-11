package com.crm.core.im.webservice;

import com.crm.core.im.entity.IMUser;
import com.crm.core.im.service.IMUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.wah.doraemon.security.response.Responsed;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/api/1.0/im")
public class IMUserRestController{

    @Autowired
    private IMUserService imUserService;

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Responsed<IMUser> getIMUserInfo(HttpServletRequest request){
        String ticket = request.getHeader("ticket");
        IMUser user = imUserService.getByTicket(ticket);

        return new Responsed<IMUser>("查询成功", user);
    }
}

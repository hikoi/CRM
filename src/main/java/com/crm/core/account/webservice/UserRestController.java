package com.crm.core.account.webservice;

import com.crm.core.account.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.wah.doraemon.entity.User;
import org.wah.doraemon.security.response.Responsed;

@RestController
@RequestMapping(value = "/api/1.0/user")
public class UserRestController{

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/token/{token}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Responsed<User> getByToken(@PathVariable("token") String token){
        User user = userService.getByToken(token);

        return new Responsed<User>("查询成功", user);
    }
}

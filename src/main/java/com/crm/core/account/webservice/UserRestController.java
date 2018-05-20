package com.crm.core.account.webservice;

import com.crm.core.account.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.wah.doraemon.entity.User;
import org.wah.doraemon.security.request.Page;
import org.wah.doraemon.security.request.PageRequest;
import org.wah.doraemon.security.response.Responsed;

@RestController
@RequestMapping(value = "/api/1.0/user")
public class UserRestController{

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/{accountId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Responsed<User> getByAccountId(@PathVariable("accountId") String accountId){
        User user = userService.getByAccountId(accountId);

        return new Responsed<User>("查询成功", user);
    }

    @RequestMapping(value = "/page", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Responsed<Page<User>> page(Long pageNum, Long pageSize, String nickname, String name){
        PageRequest pageRequest = new PageRequest(pageNum, pageSize);
        Page<User> page = userService.page(pageRequest, nickname, name);

        return new Responsed<Page<User>>("查询成功", page);
    }
}

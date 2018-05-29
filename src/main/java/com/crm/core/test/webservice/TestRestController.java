package com.crm.core.test.webservice;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.wah.doraemon.security.response.Responsed;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(value = "/api/1.0/test")
public class TestRestController{

    @RequestMapping(value = "/get", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Responsed<String> get(HttpServletRequest request){
        HttpSession session = request.getSession();
        String str = (String) session.getAttribute("test");

        return new Responsed<String>("查询成功", str);
    }

    @RequestMapping(value = "/save", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Responsed save(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.setAttribute("test", "test");

        return new Responsed("测试成功");
    }
}

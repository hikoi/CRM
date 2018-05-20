package com.crm.core.page.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/page/backstage")
public class BackstageController{

    /**
     *  后台首页
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView index(){
        return new ModelAndView("backstage/index");
    }

    /**
     *  账户管理
     *  首页
     */
    @RequestMapping(value = "/account/index", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView accountIndex(){
        return new ModelAndView("backstage/account/index");
    }

    /**
     *  微信管理
     *  首页
     */
    @RequestMapping(value = "/wechat/index", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView wechatIndex(){
        return new ModelAndView("backstage/wechat/index");
    }
}

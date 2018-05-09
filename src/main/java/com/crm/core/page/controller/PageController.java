package com.crm.core.page.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "")
public class PageController{

    /**
     * 首页
     */
    @RequestMapping(value = {"", "/index"}, method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView index(){
        return new ModelAndView("index");
    }

    /**
     * 客户端不支持提示页
     */
    @RequestMapping(value = "/page/browserNotSupport", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView browserNotSupport(){
        return new ModelAndView("browserNotSupport");
    }
}

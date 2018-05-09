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
     * 分组首页
     */
    @RequestMapping(value = "/group/index", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView groupIndex(){
        return new ModelAndView("backstage/group/index");
    }

    /**
     * 分组编辑页
     */
    @RequestMapping(value = "/group/edit", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView groupEdit(){
        return new ModelAndView("backstage/group/edit");
    }
}

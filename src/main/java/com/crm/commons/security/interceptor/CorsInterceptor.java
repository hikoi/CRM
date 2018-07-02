package com.crm.commons.security.interceptor;

//import com.crm.core.call.utils.AddressUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CorsInterceptor extends HandlerInterceptorAdapter{

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{

        response.setHeader("Access-Control-Allow-Origin", "*");
        //所有请求头
        response.setHeader("Access-Control-Allow-Headers", "*");
        //所有请求类型
        response.setHeader("Access-Control-Allow-Methods", "*");
        //允许cookies
        response.setHeader("Access-Control-Allow-Credentials", "true");

        return true;
    }
}

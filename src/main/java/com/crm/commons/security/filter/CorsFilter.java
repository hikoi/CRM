package com.crm.commons.security.filter;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CorsFilter implements Filter{

    private String origin = "*";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException{
        String custom = filterConfig.getInitParameter("origin");

        if(StringUtils.isNotBlank(custom)){
            origin = custom;
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException{
        HttpServletRequest  request  = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //所有域名
        response.setHeader("Access-Control-Allow-Origin", origin);
        //所有请求头
        response.setHeader("Access-Control-Allow-Headers", "*");
        //所有请求类型
        response.setHeader("Access-Control-Allow-Methods", "*");
        //允许cookies
        response.setHeader("Access-Control-Allow-Credentials", "true");

        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy(){

    }
}


package com.crm.commons.security.interceptor;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import redis.clients.jedis.ShardedJedisPool;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class PermissionInterceptor extends HandlerInterceptorAdapter{

    @Autowired
    private ShardedJedisPool shardedJedisPool;

    //路径匹配器
    private AntPathMatcher matcher = new AntPathMatcher();

    @Setter
    private List<String> excludes;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        String        url    = request.getRequestURI().substring(request.getContextPath().length());
        RequestMethod method = RequestMethod.valueOf(request.getMethod().toUpperCase());

        //忽略
        if(excludes != null && !excludes.isEmpty()){
            for(String exclude : excludes){
                if(matcher.match(exclude, url)){
                    return true;
                }
            }
        }

        return true;
    }
}

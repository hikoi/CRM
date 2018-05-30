package com.crm.commons.security.interceptor;

import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.wah.doraemon.security.exception.AuthenticationException;
import org.wah.doraemon.security.response.Responsed;
import org.wah.ferryman.entity.Ticket;
import org.wah.ferryman.security.consts.HttpHeaderName;
import org.wah.ferryman.utils.TicketUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AuthenticationInterceptor extends HandlerInterceptorAdapter{

    //路径匹配器
    private AntPathMatcher matcher = new AntPathMatcher();

    @Setter
    private List<String> excludes;

    @Setter
    private String server;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        String url = request.getRequestURI().substring(request.getContextPath().length());

        //忽略
        if(excludes != null && !excludes.isEmpty()){
            for(String exclude : excludes){
                if(matcher.match(exclude, url)){
                    return true;
                }
            }
        }

        String token = request.getHeader(HttpHeaderName.AUTHORIZATION);

        if(StringUtils.isBlank(token)){
            throw new AuthenticationException("无效的票据Token");
        }

        Responsed<Ticket> responsed = TicketUtils.authenticate(server, token);

        if(!responsed.getSuccess() || responsed.getResult() == null){
            throw new AuthenticationException(responsed.getMsg());
        }

        response.setHeader(HttpHeaderName.AUTHORIZATION, responsed.getResult().getToken());
        return true;
    }
}

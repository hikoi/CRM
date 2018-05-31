package com.crm.commons.security.interceptor;

import com.crm.commons.consts.Constants;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.wah.doraemon.security.exception.AuthenticationException;
import org.wah.doraemon.security.exception.TicketAuthenticationException;
import org.wah.doraemon.security.exception.TicketRefreshFailException;
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
            throw new TicketAuthenticationException("无效的票据Token");
        }

        Responsed responsed = TicketUtils.authenticate(Constants.SSO_SERVER, token);

        if(!responsed.getSuccess()){
            throw new TicketAuthenticationException(responsed.getMsg());
        }

        return true;
    }
}
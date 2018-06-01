package com.crm.commons.security.handler;

import com.crm.commons.consts.Constants;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.wah.doraemon.security.exception.TicketAuthenticationException;
import org.wah.doraemon.security.response.Responsed;
import org.wah.ferryman.security.consts.HttpHeaderName;
import org.wah.ferryman.utils.TicketUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Aspect
public class TokenAdvice{

    //路径匹配器
    private AntPathMatcher matcher = new AntPathMatcher();

    @Getter
    @Setter
    private List<String> excludes;


    @Before("execution(* com.crm.core..webservice.*.*(..))")
    public void authenticate() throws Exception{
        HttpServletRequest  request  = ((ServletRequestAttributes) (RequestContextHolder.getRequestAttributes())).getRequest();
        HttpServletResponse response = ((ServletRequestAttributes) (RequestContextHolder.getRequestAttributes())).getResponse();

        String url = request.getRequestURI().substring(request.getContextPath().length());

        //忽略
        if(excludes != null && !excludes.isEmpty()){
            for(String exclude : excludes){
                if(matcher.match(exclude, url)){
                    return;
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
    }

    @After("execution(* com.crm.core..webservice.*.*(..))")
    public void refresh() throws Exception{
        HttpServletRequest  request  = ((ServletRequestAttributes) (RequestContextHolder.getRequestAttributes())).getRequest();
        HttpServletResponse response = ((ServletRequestAttributes) (RequestContextHolder.getRequestAttributes())).getResponse();

        String url = request.getRequestURI().substring(request.getContextPath().length());

        //忽略
        if(excludes != null && !excludes.isEmpty()){
            for(String exclude : excludes){
                if(matcher.match(exclude, url)){
                    return;
                }
            }
        }

        //原Token
        String token = request.getHeader(HttpHeaderName.AUTHORIZATION);

        if(StringUtils.isNotBlank(token)){
            //刷新Token
            Responsed<String> responsed = TicketUtils.refresh(Constants.SSO_SERVER, token);

            response.setHeader(HttpHeaderName.AUTHORIZATION, responsed.getResult());
        }
    }
}



package com.crm.commons.security.handler;

import com.crm.commons.consts.Constants;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.wah.doraemon.security.response.Responsed;
import org.wah.ferryman.security.consts.HttpHeaderName;
import org.wah.ferryman.utils.TicketUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Aspect
@Component
public class RefreshTokenAdvice{

    @Around("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void refresh() throws Exception{
        HttpServletRequest  request  = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();

        //原Token
        String token = request.getHeader(HttpHeaderName.AUTHORIZATION);

        if(StringUtils.isNotBlank(token)){
            //刷新Token
            Responsed<String> responsed = TicketUtils.refresh(Constants.SSO_SERVER, token);

            response.setHeader(HttpHeaderName.AUTHORIZATION, responsed.getResult());
        }
    }
}

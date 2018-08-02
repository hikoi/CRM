package com.crm.commons.security.interceptor;

import com.crm.commons.consts.CacheName;
import com.crm.core.authentication.entity.ServiceTicket;
import com.crm.core.permission.entity.Function;
import com.google.gson.reflect.TypeToken;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.wah.doraemon.security.consts.ResponseCode;
import org.wah.doraemon.security.exception.ForbiddenException;
import org.wah.doraemon.security.exception.TicketAuthenticationException;
import org.wah.doraemon.security.response.Responsed;
import org.wah.doraemon.utils.GsonUtils;
import org.wah.doraemon.utils.RedisUtils;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.MessageFormat;
import java.util.List;

public class PermissionInterceptor extends HandlerInterceptorAdapter{

    //路径匹配器
    private AntPathMatcher matcher = new AntPathMatcher();

    private String RETURN_TYPE = "application/json;charset=UTF-8";

    @Autowired
    private ShardedJedisPool pool;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        try(ShardedJedis jedis = pool.getResource()){
            //获取请求路径
            String        url    = request.getRequestURI().substring(request.getContextPath().length());
            RequestMethod method = RequestMethod.valueOf(request.getMethod().toUpperCase());

            if(RequestMethod.PATCH.equals(method)){
                return true;
            }

            //不需授权的功能
            List<Function> needNotAllot = RedisUtils.get(jedis, CacheName.SERVICE_FUNCTION, new TypeToken<List<Function>>(){}.getType());
            for(Function exclude : needNotAllot){
                if(matcher.match(exclude.getUrl(), url) && exclude.getMethod().equals(method)){
                    return true;
                }
            }

            //验证票据
            String ticket = request.getHeader("ticket");

            if(StringUtils.isBlank(ticket)){
                Responsed responsed = new Responsed("票据不能为空", ResponseCode.TICKET_AUTHENTICATE_FAIL, false);
                response.setContentType(RETURN_TYPE);
                response.getWriter().write(GsonUtils.serialize(responsed));
                return false;
            }

            //查询票据
            ServiceTicket st = RedisUtils.get(jedis, CacheName.SERVICE_TICKET + ticket, ServiceTicket.class);

            if(st == null || StringUtils.isBlank(st.getAccountId())){
                Responsed responsed = new Responsed("票据验证失败,请重新登录", ResponseCode.TICKET_AUTHENTICATE_FAIL, false);
                response.setContentType(RETURN_TYPE);
                response.getWriter().write(GsonUtils.serialize(responsed));
                return false;
            }

            //查询功能权限
            List<Function> has = RedisUtils.get(jedis, CacheName.USER_FUNCTION + st.getAccountId(), new TypeToken<List<Function>>(){}.getType());

            if(has != null && !has.isEmpty()){
                for(Function exclude : has){
                    if(matcher.match(exclude.getUrl(), url) && exclude.getMethod().equals(method)){
                        //票据续期
                        RedisUtils.expire(jedis, CacheName.SERVICE_TICKET + ticket, CacheName.TICKET_EXPIRE);
                        //功能权限续期
                        RedisUtils.expire(jedis, CacheName.USER_FUNCTION + st.getAccountId(), CacheName.TICKET_EXPIRE);
                        return true;
                    }
                }
            }

            Responsed responsed = new Responsed(MessageFormat.format("您没有权限访问[{0}]", url), ResponseCode.FORBIDDEN, false);
            response.setContentType(RETURN_TYPE);
            response.getWriter().write(GsonUtils.serialize(responsed));
            return false;
        }
    }
}

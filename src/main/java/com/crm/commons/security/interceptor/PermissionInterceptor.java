package com.crm.commons.security.interceptor;

import com.crm.commons.consts.CacheName;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.wah.doraemon.security.exception.TicketAuthenticationException;
import org.wah.doraemon.utils.RedisUtils;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class PermissionInterceptor extends HandlerInterceptorAdapter{

    //路径匹配器
    private AntPathMatcher matcher = new AntPathMatcher();

    @Autowired
    private ShardedJedisPool pool;

    @Getter
    @Setter
    private List<String> excludes;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        try(ShardedJedis jedis = pool.getResource()){
            //获取请求路径
            String        url    = request.getRequestURI().substring(request.getContextPath().length());
            RequestMethod method = RequestMethod.valueOf(request.getMethod().toUpperCase());

            if(excludes != null && !excludes.isEmpty()){
                for(String exclude : excludes){
                    if(matcher.match(exclude, url)){
                        return true;
                    }
                }
            }

            //验证票据
            String ticket = request.getHeader("ticket");

            if(StringUtils.isBlank(ticket)){

                throw new TicketAuthenticationException("票据不能为空");
            }
            if(!RedisUtils.exists(jedis, CacheName.SERVICE_TICKET + ticket)){

                throw new TicketAuthenticationException("票据验证失败,请重新登录");
            }

            //票据续期
            RedisUtils.expire(jedis, CacheName.SERVICE_TICKET + ticket, CacheName.TICKET_EXPIRE);

            return true;
        }
    }
}

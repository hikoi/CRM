package com.crm.commons.security.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import redis.clients.jedis.ShardedJedisPool;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PermissionInterceptor extends HandlerInterceptorAdapter{

    @Autowired
    private ShardedJedisPool shardedJedisPool;

    //路径匹配器
    private static final AntPathMatcher matcher = new AntPathMatcher();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
//        //请求URL
//        String url = request.getRequestURI().substring(request.getContextPath().length());
//        RequestMethod method = RequestMethod.valueOf(request.getMethod().toUpperCase());
//
//        //非需授权URL
//        ShardedJedis jedis = shardedJedisPool.getResource();
//        List<Permission> needNotAllots = RedisUtils.get(jedis, CacheName.NEED_NOT_ALLOT_URL, new TypeToken<List<Permission>>(){}.getType());
//
//        if(needNotAllots != null && !needNotAllots.isEmpty()){
//            for(Permission needNotAllot : needNotAllots){
//                if(matcher.match(needNotAllot.getUrl(), url) && needNotAllot.getMethod().equals(method)){
//                    RedisUtils.close(jedis);
//                    return true;
//                }
//            }
//        }
//
//        //账户ID
//        String accountId = (String) request.getSession().getAttribute(SessionName.ACCOUNT_ID);
//        if(StringUtils.isBlank(accountId)){
//            throw new LoginFailException("无效的登录状态");
//        }
//
//        //检查登录状态
//        if(RedisUtils.sismemeber(jedis, CacheName.LOGIN_FAIL, accountId)){
//            throw new LoginFailException("无效的登录状态");
//        }
//
//        //权限验证
//        Set<Permission> userPermissions = RedisUtils.get(jedis, CacheName.USER_PERMISSION + accountId, new TypeToken<Set<Permission>>(){}.getType());
//        if(userPermissions != null && !userPermissions.isEmpty()){
//            for(Permission permission : userPermissions){
//                if(matcher.match(permission.getUrl(), url) && permission.getMethod().equals(method)){
//                    RedisUtils.close(jedis);
//                    return true;
//                }
//            }
//        }
//
//        RedisUtils.close(jedis);
//
//        //无访问权限
//        throw new ForbiddenException("无访问权限");

        return true;
    }
}

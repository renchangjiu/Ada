package com.su.interceptor;


import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.su.utils.JsonUtil;
import com.su.utils.RedisOperator;
import com.su.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.su.pojo.*;

import java.util.concurrent.TimeUnit;


/**
 * 管理员登录拦截器
 *
 * @author su
 */
@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisOperator redis;

    private final String preToken = "TOKEN:";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setContentType("application/json; charset=utf-8");
        ServletOutputStream writer = response.getOutputStream();
        String token = request.getHeader("token");
        // 没有token header
        if (StringUtil.isEmpty(token) || "null".equals(token)) {
            Result result = Result.failed(401, "need header:token");
            writer.print(JsonUtil.objectToJson(result));
            return false;
        }
        // Redis 里没有该token
        if (!redis.exists(preToken + token)) {
            Result result = Result.failed(401, "Need to SignIn");
            writer.print(JsonUtil.objectToJson(result));
            return false;
        }
        // 通过拦截器, 刷新该 token 的过期时间
        redis.expire(preToken + token, 60 * 60 * 24L, TimeUnit.SECONDS);
        return true;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
    }
}

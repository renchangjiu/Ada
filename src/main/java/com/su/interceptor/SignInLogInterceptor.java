package com.su.interceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.su.pojo.SignInLog;
import com.su.service.SignInLogService;
import com.su.utils.IpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


/**
 * 记录登录日志
 *
 * @author su
 */
@Component
public class SignInLogInterceptor implements HandlerInterceptor {

    @Autowired
    private SignInLogService ss;


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        SignInLog log = new SignInLog();
        log.setIp(IpUtil.getIp(request));
        ss.insert(log);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
    }
}

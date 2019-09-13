package com.su.config;

import com.su.interceptor.AuthInterceptor;
import com.su.interceptor.ReadLogInterceptor;
import com.su.interceptor.SignInLogInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter {


    // 提前注入
    @Autowired
    private AuthInterceptor authInterceptor;

    @Autowired
    private ReadLogInterceptor readLogInterceptor;

    @Autowired
    private SignInLogInterceptor signInLogInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 拦截需要登录权限的api
        registry.addInterceptor(authInterceptor).addPathPatterns("/api/admin/**");

        registry.addInterceptor(readLogInterceptor).addPathPatterns("/api/article/**");

        registry.addInterceptor(signInLogInterceptor).addPathPatterns("/api/sign-in");
        super.addInterceptors(registry);
    }


}

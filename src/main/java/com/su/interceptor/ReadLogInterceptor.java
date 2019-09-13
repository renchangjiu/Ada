package com.su.interceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.su.exception.MyException;
import com.su.pojo.Article;
import com.su.pojo.ReadLog;
import com.su.service.ArticleService;
import com.su.service.ReadLogService;
import com.su.utils.IpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


/**
 * 记录访问日志
 */
@Component
public class ReadLogInterceptor implements HandlerInterceptor {

    @Autowired
    private ReadLogService rs;

    @Autowired
    private ArticleService as;

    @Override
    @Transactional
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
    }

    @Override
    @Transactional
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        String uri = request.getRequestURI();       // uri = "/api/article/85"
        Integer artId = Integer.valueOf(uri.substring(uri.lastIndexOf("/") + 1));   // 获取文章id

        try {
            String ip = IpUtil.getIp(request);
            Article article = as.findById(artId);               // 当有此文章时
            ReadLog log = new ReadLog();
            log.setArticle(article);
            log.setIp(ip);
            // 添加记录, 同时t_article : readNum + 1
            as.increment(artId);
            rs.insert(log);
        } catch (MyException me) {
            System.out.println("用户访问了一篇不存在的文章");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

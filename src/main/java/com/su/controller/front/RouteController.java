package com.su.controller.front;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

/**
 * @author su
 * @date 2019/10/18 17:01
 */
@Controller("frontEndRouteController")
public class RouteController {

    /**
     * 前台首页
     */
    @RequestMapping("/")
    public String index() {
        return "index";
    }

    /**
     * 关于
     */
    @RequestMapping("/about")
    public String about() {
        return "about";
    }

    /**
     * 联系
     */
    @RequestMapping("/contact")
    public String contact() {
        return "contact";
    }


    /**
     * 文章
     */
    @RequestMapping("/article/{id}")
    public String article(@PathVariable Integer id) {
        return "article";
    }


    /**
     * 管理员登录
     */
    @RequestMapping("/admin/sign-in")
    public String signIn() {
        return "back-end/sign-in";
    }

    /**
     * 404
     */
    @RequestMapping("/404")
    public String notFound() {
        return "error/404";
    }


    @RequestMapping("/html")
    public void html(HttpServletResponse response) {
        String path = "D:/OneDrive/文档/notes/Java基础/浅析JAVA中过滤器、监听器、拦截器的区别.html";
        PrintWriter pw = null;
        response.setHeader("Content-Type", "text/html;charset=UTF-8");
        try {
            pw = response.getWriter();
            String s = IOUtils.toString(new FileInputStream(path), StandardCharsets.UTF_8);
            pw.write(s);
            pw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            assert pw != null;
            pw.close();
        }
    }

}

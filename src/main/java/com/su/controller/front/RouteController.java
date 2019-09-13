package com.su.controller.front;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("frontEndRouteController")
public class RouteController {

    // 前台首页
    @RequestMapping("/")
    public String index() {
        return "index";
    }

    // 关于
    @RequestMapping("/about")
    public String about() {
        return "about";
    }

    // 联系
    @RequestMapping("/contact")
    public String contact() {
        return "contact";
    }

    // 文章
    @RequestMapping("/article/{id}")
    public String article(@PathVariable Integer id) {
        return "article";
    }


    // 管理员登录
    @RequestMapping("/admin/sign-in")
    public String signIn() {
        return "back-end/sign-in";
    }

    // 按标签id搜索
    // @RequestMapping("/tag/articles/{tagId}")
    public String findArticleByTag() {
        return "search";
    }

    // 404
    @RequestMapping("/404")
    public String notFound() {
        return "error/404";
    }

}

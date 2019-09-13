package com.su.controller.back;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("backEndRouteController")
@RequestMapping("/admin")
public class RouteController {


    // todo 草稿箱


    @RequestMapping("")
    public String index() {
        return "back-end/index";
    }

    @RequestMapping("/article/write")
    public String writeArticle() {
        return "back-end/write-article";
    }

    @RequestMapping("/article/update/{id}")
    public String updateArticle(@PathVariable Integer id) {
        return "back-end/update-article";
    }

    @RequestMapping("/letter")
    public String letter() {
        return "back-end/letter";
    }

    @RequestMapping("/letter/{id}")
    public String letterDetail(@PathVariable Integer id) {
        return "back-end/letter-detail";
    }

    @RequestMapping("/read-log")
    public String readLog() {
        return "back-end/read-log";
    }

    @RequestMapping("/sign-in-log")
    public String signInLog() {
        return "back-end/sign-in-log";
    }

    @RequestMapping("/comment")
    public String comment() {
        return "back-end/comment";
    }

    @RequestMapping("/tag")
    public String tag() {
        return "back-end/tag";
    }

    @RequestMapping("/operation")
    public String operation() {
        return "back-end/operation";
    }
}

package com.su.controller;

import com.su.pojo.Article;
import com.su.pojo.Result;
import com.su.service.ArticleService;
import com.su.utils.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class TestController {

    @Autowired
    private ArticleService as;

    @RequestMapping("/test")
    public Result test() throws Exception {
        Log.makeMark();
        Article ar = as.findById(85);
        Log.println(ar.getEditTime());
        return Result.success(as.findById(85));
    }


}

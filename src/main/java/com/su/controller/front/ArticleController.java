package com.su.controller.front;

import com.su.pojo.Article;
import com.su.pojo.Result;
import com.su.service.ArticleService;
import com.su.utils.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author su
 */
@RestController("frontEndArticleController")
@RequestMapping("/api")
public class ArticleController {

    @Autowired
    private ArticleService as;


    /**
     * 分页返回文章列表
     */
    @RequestMapping("/articles/{page}")
    public Result list(@PathVariable Integer page) throws Exception {
        return Result.success(as.list(page, 5));
    }

    @RequestMapping("/article/{id}")
    public Result show(@PathVariable String id) throws Exception {
        Article article = as.findById(Integer.parseInt(id));
        if (article == null) {
            return Result.failed(414, "目标文章没有找到");
        }
        return Result.success(article);
    }


    /**
     * 按文章内容搜索
     */
    @RequestMapping("/search")
    public Result search(String input) throws Exception {
        return Result.success(as.search(input));
    }


    /**
     * 返回请求标签下文章列表
     */
    @RequestMapping("/tag/articles/{tagId}")
    public Result findListByTagId(@PathVariable String tagId) throws Exception {
        List<Article> articles = as.findListByTagId(Integer.parseInt(tagId));
        if (articles.size() == 0) {
            return Result.failed(416, "请求标签下没有文章");
        }
        return Result.success(articles);
    }


}

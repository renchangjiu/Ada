package com.su.service;

import java.util.List;

import com.su.mapper.ArticleMapper;
import com.su.mapper.TagMapper;
import com.su.pojo.Article;
import com.su.pojo.Result;
import com.su.pojo.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TagService {

    @Autowired
    private TagMapper tm;

    @Autowired
    private ArticleService articleService;

    public List<Tag> list() throws Exception {
        return tm.list();
    }

    public void insert(Tag tag) throws Exception {
        tm.insert(tag);
    }

    /**
     * 删除之前判断该标签下有没有文章, 有则不删
     */
    public Result delete(Integer id) throws Exception {
        List<Article> articles = articleService.findListByTagId(id);
        if (articles.size() > 0) {
            return Result.failed(315, "当前分类下还有文章, 你需要在删除此分类下所有文章之后再执行操作");
        }
        this.tm.delete(id);
        return Result.success();
    }

    public Tag findById(Integer id) throws Exception {
        return tm.findById(id);
    }
}

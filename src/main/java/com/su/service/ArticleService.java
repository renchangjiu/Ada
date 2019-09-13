package com.su.service;

import java.util.ArrayList;
import java.util.List;

import com.su.exception.MyException;
import com.su.mapper.CommentMapper;
import com.su.mapper.TagMapper;
import com.su.pojo.PageHelper;
import com.su.utils.CommonUtil;
import com.su.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.su.mapper.ArticleMapper;
import com.su.pojo.Article;

/**
 * @author su
 */
@Service
public class ArticleService {

    @Autowired
    private ArticleMapper am;

    @Autowired
    private TagMapper tm;

    @Autowired
    private CommentMapper cm;

    @Autowired
    private TagService tagService;


    /**
     * 首页展示文章列表, 分页
     */
    public PageHelper list(Integer page, Integer pageSize) throws Exception {
        Integer totalCount = am.getTotalCount();
        PageHelper<Article> pageHelper = new PageHelper<>(page, pageSize, totalCount);
        if (page > pageHelper.getTotalPage()) {
            throw new MyException(0, "The target page number exceeds the maximum page number.");
        }
        List<Article> list = am.list(pageHelper.getIndex(), pageSize);
        for (Article article : list) {
            article.setTags(this.tagToArray(article.getTags()));
        }
        pageHelper.setObjects(list);
        return pageHelper;
    }

    /**
     * tag_ids  -> tag_name, 结果为: name1 name2 name3..
     */
    private String tagToArray(String tags) throws Exception {
        String[] idArr = tags.split("\\|");
        StringBuilder tagNames = new StringBuilder();
        for (String strId : idArr) {
            Integer id = Integer.valueOf(strId);
            String tagName = tm.findNameById(id);
            tagNames.append(tagName).append(" ");
        }
        return tagNames.toString();
    }


    /**
     * 根据id 查找文章, 找不到则返回null.
     *
     * @param id article id
     * @return article or null
     * @throws Exception 统一抛出
     */
    public Article findById(Integer id) throws Exception {
        Article article = am.findArticleById(id);
        if (article == null) {
            return null;
        } else {
            article.setTags(this.tagToArray(article.getTags()));
            return article;
        }
    }

    /**
     * 查询title, summary, content
     */
    public List<Article> search(String str) throws Exception {
        return am.search(str);
    }


    public void insert(Article article) throws Exception {
        // 转义
        article.setTitle(StringUtil.htmlSpecialCharsEncode(article.getTitle()));
        article.setSummary(StringUtil.htmlSpecialCharsEncode(article.getSummary()));
        am.insert(article);
    }

    // 修改文章
    public void update(Article article) throws Exception {
        // 转义
        article.setTitle(StringUtil.htmlSpecialCharsEncode(article.getTitle()));
        article.setSummary(StringUtil.htmlSpecialCharsEncode(article.getSummary()));
        am.update(article);
    }

    /**
     * 阅读数 + 1
     */
    public void increment(Integer id) throws Exception {
        am.increment(id);
    }

    public void delete(Integer artId) throws Exception {
        // 先删评论
        cm.deleteByArticleId(artId);
        // 后删文章
        am.delById(artId);
    }


    /**
     * 根据catId 查询文章列表
     *
     * @param tagId tag id
     * @return 文章列表
     * @throws Exception 统一抛出
     */
    public List<Article> findListByTagId(Integer tagId) throws Exception {
        List<Article> articles = this.am.findListByTagId(tagId + "");
        List<Article> result = new ArrayList<>();
        for (Article article : articles) {
            String tags = article.getTags();
            String[] strings = tags.split("\\|");
            if (CommonUtil.inArray(strings, tagId + "")) {
                result.add(article);
            }
        }
        return result;
    }


}

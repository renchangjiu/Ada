package com.su.mapper;

import java.util.List;

import com.su.pojo.Article;
import org.apache.ibatis.annotations.Param;


public interface ArticleMapper {

    List<Article> list(@Param("index") Integer index, @Param("length") Integer length) throws Exception;

    Integer getTotalCount() throws Exception;

    Article findArticleById(Integer id) throws Exception;

    List<Article> search(String str) throws Exception;

    void insert(Article article) throws Exception;

    void delById(Integer artId) throws Exception;

    void update(Article article) throws Exception;

    void increment(Integer id) throws Exception;

    Integer exists(Integer id);

    List<Article> findListByTagId(String tagId) throws Exception;

    // List<Article> findByMultipleCondition(Article article) throws Exception;
    //
    // List<Article> fuzzyQuery(String condition) throws Exception;


}

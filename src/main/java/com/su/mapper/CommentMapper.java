package com.su.mapper;

import com.su.pojo.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;


public interface CommentMapper {

    void deleteByArticleId(Integer artId) throws Exception;

    List<Comment> list(@Param("index") Integer index, @Param("length") Integer length) throws Exception;

    Integer getTotalCount() throws Exception;

    List<Comment> findByArtId(Integer artId) throws Exception;

    Integer getTotalCountGroupByArticle(Integer articleId) throws Exception;

    List<Comment> findCommentsByArticleId(@Param("artId") Integer artId, @Param("index") Integer index, @Param("length") Integer length) throws Exception;

    Integer findMaxFloorByArtId(Integer artId) throws Exception;

    void insert(Comment comment) throws Exception;

    Date findLastInsertTimeByIp(String ip) throws Exception;

    void delById(Integer id) throws Exception;

    Comment findById(Integer id) throws Exception;

}

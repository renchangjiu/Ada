package com.su.service;


import com.su.exception.MyException;
import com.su.mapper.CommentMapper;
import com.su.pojo.Comment;
import com.su.pojo.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.su.mapper.ArticleMapper;

import java.util.Date;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentMapper cm;

    @Autowired
    private ArticleMapper am;


    public PageHelper list(Integer page, Integer pageSize) throws Exception {
        Integer totalCount = cm.getTotalCount();
        PageHelper<Comment> pageHelper = new PageHelper<>(page, pageSize, totalCount);
        if (page > pageHelper.getTotalPage()) {
            throw new MyException(0, "The target page number exceeds the maximum page number.");
        }
        List<Comment> list = cm.list(pageHelper.getIndex(), pageSize);
        pageHelper.setObjects(list);
        return pageHelper;
    }

    /**
     * find comments by article id, 分页
     */
    public PageHelper findCommentsByArticleId(Integer artId, Integer page, Integer pageSize) throws Exception {
        Integer totalCount = cm.getTotalCountGroupByArticle(artId);
        PageHelper<Comment> pageHelper = new PageHelper<>(page, pageSize, totalCount);
        if (page > pageHelper.getTotalPage()) {
            throw new MyException(0, "The target page number exceeds the maximum page number.");
        }
        List<Comment> list = cm.findCommentsByArticleId(artId, pageHelper.getIndex(), pageSize);
        pageHelper.setObjects(list);
        return pageHelper;
    }


    // 添加评论
    public void insert(Comment comment) throws Exception {
        if (am.exists(comment.getArticle().getId()) == null) {
            throw new MyException(0, "没有此文章");
        }
        // 补齐数据
        Integer maxFloor = cm.findMaxFloorByArtId(comment.getArticle().getId());

        if (maxFloor == null) {
            comment.setFloor(1);
        } else {
            comment.setFloor(maxFloor + 1);
        }
        // 5秒内不允许两次提交评论
        Date lastTime = cm.findLastInsertTimeByIp(comment.getIp());
        Date now = new Date();
        long maxSecBetweenTwoSend = 5L;
        if (lastTime != null) {
            if ((now.getTime() - lastTime.getTime()) * 1000 < maxSecBetweenTwoSend) {
                throw new MyException(0, "请不要在5秒内提交两次评论ヘ(_ _ヘ)");
            }
        }
        // 完成添加
        cm.insert(comment);
    }


    public void delete(Integer id) throws Exception {
        cm.delById(id);
    }

    public Comment findById(Integer id) throws Exception {
        return cm.findById(id);
    }
}

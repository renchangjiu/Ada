package com.su.controller.front;


import com.su.pojo.Comment;
import com.su.service.ArticleService;
import com.su.utils.IpUtil;
import com.su.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.su.pojo.Result;
import com.su.service.CommentService;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 后台comment 管理
 *
 * @author su
 */
@RestController("frontEndCommentController")
@RequestMapping("/api")
public class CommentController {

    @Autowired
    private CommentService cs;

    // 提交评论
    @RequestMapping("/comment/send")
    public Result send(Comment comment, HttpServletRequest request) throws Exception {
        // 数据完整性校验
        if (StringUtil.isEmpty(comment.getContent())) {
            return Result.error("评论内容不能为空");
        }
        if (StringUtil.isEmpty(comment.getName())) {
            comment.setName("匿名");
        }
        String ip = IpUtil.getIp(request);
        comment.setIp(ip);
        cs.insert(comment);
        return Result.success(1, "success");
    }

    // 获取某文章下所有评论, 分页
    @RequestMapping("/comments/{articleId}/{page}")
    public Result findCommentsByArticleId(@PathVariable Integer articleId, @PathVariable Integer page) throws Exception {
        return Result.success(cs.findCommentsByArticleId(articleId, page, 5));
    }

}

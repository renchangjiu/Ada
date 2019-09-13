package com.su.controller.back;

import com.su.pojo.Result;
import com.su.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 后台comment 管理
 *
 * @author su
 */
@RestController("backEndCommentController")
@RequestMapping("/api/admin")
public class CommentController {

    @Autowired
    private CommentService cs;

    @RequestMapping("/comments/{page}")
    public Result list(@PathVariable() Integer page) throws Exception {
        return Result.success(cs.list(page, 10));
    }

    @RequestMapping("/comment/delete/{id}")
    public Result delById(@PathVariable Integer id) throws Exception {
        cs.delete(id);
        return Result.success(1, "success");
    }

}

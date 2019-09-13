package com.su.controller.back;

import com.su.pojo.Result;
import com.su.service.LetterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 后台私信管理
 *
 * @author su
 */
@RestController("backEndLetterController")
@RequestMapping("/api/admin")
public class LetterController {

    @Autowired
    private LetterService ls;

    @RequestMapping("/letters/{page}")
    public Result list(@PathVariable("page") Integer page) throws Exception {
        return Result.success(ls.list(page, 10));
    }

    @RequestMapping("/letter/delete/{id}")
    // 删除
    public Result delLetterByLetId(@PathVariable("id") Integer id) throws Exception {
        ls.delete(id);
        return Result.success(1, "删除私信成功");
    }

    @RequestMapping("/letter/{id}")
    public Result show(@PathVariable("id") Integer id) throws Exception {
        return Result.success(ls.findById(id));
    }


}
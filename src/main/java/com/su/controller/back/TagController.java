package com.su.controller.back;

import com.su.pojo.Result;
import com.su.pojo.Tag;
import com.su.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * 后台分类管理
 *
 * @author su
 */
@RestController("backEndTagController")
@RequestMapping("/api/admin")
public class TagController {

    @Autowired
    private TagService ts;


    @RequestMapping("/tags")
    public Result list() throws Exception {
        return Result.success(ts.list());
    }


    @RequestMapping("/tag/delete/{id}")
    public Result delete(@PathVariable Integer id) throws Exception {
        // ts.delete(id);
        // return Result.success(1, "success");
        return this.ts.delete(id);
    }


    @RequestMapping("/tag/insert")
    public Result insert(Tag tag) throws Exception {
        // 校验
        if (tag.getName().trim().length() < 2 || tag.getName().trim().length() > 20) {
            return Result.failed("标签名称应大于等于2个字符并小于等于20个字符");
        }
        ts.insert(tag);
        return Result.success(1, "success");
    }

}

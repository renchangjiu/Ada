package com.su.controller.front;

import com.su.pojo.Letter;
import com.su.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import com.su.pojo.Result;
import com.su.service.LetterService;
import org.springframework.web.bind.annotation.RestController;

/**
 * 私信管理
 *
 * @author su
 */

@RestController("frontEndLetterController")
@RequestMapping("/api")
public class LetterController {

    @Autowired
    private LetterService ls;

    @RequestMapping("/letter/send")
    public Result send(Letter letter) throws Exception {
        // 1. 数据合法性校验
        if (StringUtil.isEmpty(letter.getMessage())) {
            return Result.failed("message是必填项");
        }
        // 2. 为非必填项设置默认值
        if (StringUtil.isEmpty(letter.getName())) {
            letter.setName("unknown");
        }
        if (StringUtil.isEmpty(letter.getEmail())) {
            letter.setEmail("unknown");
        }
        ls.insert(letter);
        return Result.success(1, "私信成功, 博主会尽快查看ヘ(_ _ヘ)");
    }

}
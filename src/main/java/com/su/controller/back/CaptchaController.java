package com.su.controller.back;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.su.pojo.Captcha;
import com.su.pojo.Result;
import com.su.utils.CaptchaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CaptchaController {

    @Autowired
    private StringRedisTemplate redis;

    //请求验证码, 返回id 及base64码
    @RequestMapping("/captcha")
    public Result captcha(HttpServletRequest request, HttpServletResponse response) throws IOException {


        Captcha captcha = CaptchaUtil.getCaptcha();
        // key: uuid, value: 验证码图片答案
        redis.opsForValue().set("captchaId:" + captcha.getId(), captcha.getValue());
        redis.expire("captchaId:" + captcha.getId(), 60, TimeUnit.SECONDS);

        return Result.success(captcha);
    }


    //校验验证码
    // @RequestMapping("/checkVCode")
    public String login(String checkCode, HttpServletRequest request) throws IOException {
        String picCode = (String) request.getSession().getAttribute("picCode");
        String result = "false";
        if (checkCode.equalsIgnoreCase(picCode)) {
            result = "true";
        }
        return result;
    }
}



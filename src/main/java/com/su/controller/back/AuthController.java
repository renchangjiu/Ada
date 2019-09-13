package com.su.controller.back;

import com.su.exception.MyException;
import com.su.pojo.Admin;
import com.su.pojo.Result;
import com.su.utils.*;
import com.su.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private AuthService as;

    @Autowired
    private RedisOperator redis;

    // 登录
    @RequestMapping("/sign-in")
    public Result signIn(Admin formAdmin, String captchaId, String captchaValue, HttpServletRequest request) {
        // 数据完整性校验
        if (StringUtil.isEmpty(formAdmin.getName()) || StringUtil.isEmpty(formAdmin.getPassword())) {
            return Result.failed("请填写用户名或密码或验证码");
        }

        String ip = IpUtil.getIp(request);

        // 验证码校验
        if (!validateCaptcha(captchaId, captchaValue, ip)) {
            return Result.failed("验证码错误");
        }
        try {
            Admin admin = as.signIn(formAdmin);
            // 校验通过, 用户信息写入redis, key: token, value: user, 设置过期时间
            String token = UUIDUtil.uuid();
            String adminJson = JsonUtil.objectToJson(admin);
            redis.set("TOKEN:" + token, adminJson);
            redis.expire("TOKEN:" + token, 60 * 60 * 24L, TimeUnit.SECONDS);
            this.setNotNeedCaptcha(ip);
            // 返回token
            return Result.success(token, 1);
        } catch (MyException me) {
            // 如果登录失败, 则设置需要验证码, 方法是: 在Redis设置一个key为 "ip", value为y的键值对
            this.setNeedCaptcha(ip);
            return Result.failed(me.getData().toString());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    private void setNeedCaptcha(String ip) {
        String key = "need-captcha:" + ip;
        redis.set(key, "y");
        redis.expire(key, 30L, TimeUnit.SECONDS);
    }


    private void setNotNeedCaptcha(String ip) {
        String key = "need-captcha:" + ip;
        redis.delete(key);
    }


    // 判断当前登录人是否需要验证码
    private boolean isNeedCaptcha(String ip) {
        String key = "need-captcha:" + ip;
        return redis.exists(key);
    }

    private boolean validateCaptcha(String captchaId, String captchaValue, String ip) {
        boolean flag = false;
        if (this.isNeedCaptcha(ip)) {
            String redisCaptchaValue = redis.get("captchaId:" + captchaId);
            if (redisCaptchaValue != null) {
                if (redisCaptchaValue.equalsIgnoreCase(captchaValue)) {
                    flag = true;
                }
            }
        } else {
            flag = true;
        }
        return flag;
        // 不用验证码的话, 就直接return true
        // return true;
    }


    @RequestMapping("/admin/sign-out")
    public Result logout(HttpServletRequest request) throws Exception {
        String headerToken = request.getHeader("token");
        if (headerToken == null) {
            return Result.success("登出成功, 但缺少header:token");
        } else {
            Boolean result = redis.delete("TOKEN:" + headerToken);
            if (result) {
                return Result.success("登出成功");
            } else {
                return Result.success("出成功, 但当前没有登录");
            }
        }
    }

}

package com.su.service;


import com.su.exception.MyException;
import com.su.pojo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.su.mapper.AuthMapper;
import com.su.pojo.Admin;
import com.su.utils.MDUtil;

/**
 * @author su
 * @date 2019/10/18 17:01
 */
@Service
public class AuthService {

    @Autowired
    private AuthMapper authMapper;


    public Result<Admin> signIn(Admin formAdmin) throws Exception {
        //数据合法性校验
        if (authMapper.findAdminByName(formAdmin.getName()) == null) {
            throw new MyException(0, "用户不存在");
        }
        // 密码进行md5计算
        formAdmin.setPassword(MDUtil.getMD5(formAdmin.getPassword()));
        Admin admin = authMapper.findAdminByNameAndPassword(formAdmin);
        if (admin == null) {
            return Result.error("用户不存在或密码错误");
        }
        return Result.success(admin);
    }


}

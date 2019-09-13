package com.su.service;


import com.su.exception.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.su.mapper.AuthMapper;
import com.su.pojo.Admin;
import com.su.utils.MDUtil;

@Service
public class AuthService {

    @Autowired
    private AuthMapper am;


    public Admin signIn(Admin formAdmin) throws Exception {
        //数据合法性校验
        if (am.findAdminByName(formAdmin.getName()) == null) {
            throw new MyException(0, "用户不存在");
        }
        // 密码进行md5计算
        formAdmin.setPassword(MDUtil.getMD5(formAdmin.getPassword()));
        Admin admin = am.findAdminByNameAndPassword(formAdmin);
        if (admin == null) {
            throw new MyException(0, "用户不存在或密码错误");
        }
        return admin;
    }


}

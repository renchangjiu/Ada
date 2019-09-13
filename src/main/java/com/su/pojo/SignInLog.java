package com.su.pojo;

import java.util.Date;

public class SignInLog {

    private Integer id; // 主键
    private Date time;  // 登录日期
    private String ip;  // 登录ip


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}

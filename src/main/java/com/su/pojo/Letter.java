package com.su.pojo;

import java.util.Date;

public class Letter {

    private String id;      // 主键
    private String name;    // 名称
    private String email;   // 对方邮箱
    private String message; // 私信内容
    private Date time;  // 发信时间
    private Integer isRead; //是否已读, 1: 未读, 2:已读

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getIsRead() {
        return isRead;
    }

    public void setIsRead(Integer isRead) {
        this.isRead = isRead;
    }

    @Override
    public String toString() {
        return "Letter{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", message='" + message + '\'' +
                ", time=" + time +
                ", isRead=" + isRead +
                '}';
    }
}

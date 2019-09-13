package com.su.pojo;


public class Admin {

    private String id;  // 主键
    private String name;     // 管理员名称
    private String password; // md5密码
    private Integer level;   // 权限等级, 2位等级

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}

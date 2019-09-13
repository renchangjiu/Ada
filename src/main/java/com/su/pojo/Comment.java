package com.su.pojo;

import java.util.Date;

public class Comment {

    private Integer id;       // 主键
    private String ip;      // ip
    private String content;  // 评论内容
    private Integer floor;   // 所在楼层
    private Date time;    // 评论时间
    private String name;     //评论人

    private Article article;    // 一对一映射文章表

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", ip='" + ip + '\'' +
                ", content='" + content + '\'' +
                ", floor=" + floor +
                ", time=" + time +
                ", name='" + name + '\'' +
                ", article=" + article +
                '}';
    }
}

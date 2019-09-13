package com.su.pojo;

import java.util.Date;

/**
 * 对数据库的操作记录
 */
public class Operation {

    private Integer id;         // 主键
    private Date time;          // 操作时间

    private String table;       // 被操作的表名, t_article, t_letter等
    private String type;        // 操作方法, insert, delete, update等
    private String message;     // 被操作对象转json

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

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    @Override
    public String toString() {
        return "Operation{" +
                "id=" + id +
                ", time=" + time +
                ", table='" + table + '\'' +
                ", type='" + type + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}

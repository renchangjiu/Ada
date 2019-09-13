package com.su.exception;

public class MyException extends Exception {

    private Integer status;     // 状态码
    private Object data;        // 携带的数据

    public MyException(Integer status, Object data) {
        this.status = status;
        this.data = data;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}

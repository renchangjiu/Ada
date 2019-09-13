package com.su.pojo;

public class UploadImageResult {

    private Boolean error;      // 是否错误
    private String path;        // 返回给前端的图片的URL
    private String msg;         // 错误信息, 前台不使用


    public UploadImageResult(Boolean error, String path, String msg) {
        this.error = error;
        this.path = path;
        this.msg = msg;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

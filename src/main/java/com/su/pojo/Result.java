package com.su.pojo;

/**
 * 返回给前台的信息包装类
 *
 * @author su
 */

public class Result {
    /**
     状态码:
     0   : 通用的失败码
     1   : 通用的成功码
     450 : 请求的页码超过最大页码
     414 : 文章没有找到
     415 : 请求参数格式错误, 如需要数字却传递了字母
     416 : 请求标签下没有文章

     315 : 请求删除有文章的标签
     */
    /**
     *
     */
    private boolean success;    // 是否成功
    private int status;         // 状态码
    private String message;     // 提示信息
    private Object data;        // 携带的数据

    /**
     * data 与 message 可能会混淆
     */
    public static Result success(Object data) {
        return new Result(true, 1, "null", data);
    }


    public static Result success(int status, String message) {
        return new Result(true, status, message);
    }

    public static Result success(Object data, int status) {
        return new Result(true, status, "null", data);
    }

    public static Result success(int status, String message, Object data) {
        return new Result(true, status, message, data);
    }

    public static Result success() {
        return new Result(true, "success");
    }


    public static Result failed(String message) {
        return new Result(false, message);
    }

    public static Result failed(int status, String message) {
        return new Result(false, status, message);
    }

    public static Result failed(Object data, int status) {
        return new Result(false, status, "null", data);
    }

    public static Result failed(int status, String message, Object data) {
        return new Result(false, status, message, data);
    }


    public static Result getResult(boolean success, int status, String message, Object data) {
        return new Result(success, status, message, data);
    }


    private Result(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    private Result(boolean success, int status, String message) {
        this.success = success;
        this.status = status;
        this.message = message;
    }

    private Result(boolean success, int status, String message, Object data) {
        this.success = success;
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}

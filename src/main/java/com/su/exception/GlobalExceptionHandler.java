package com.su.exception;

import com.su.pojo.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 全局异常处理
 *
 * @author su
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 捕获自定义的异常
     */
    @ExceptionHandler(value = MyException.class)
    @ResponseBody
    public Result myExceptionHandler(MyException e) {
        return Result.failed(450, e.getData().toString());
    }

    /**
     * 捕获 NumberFormatException
     * 例如根据id查询文章时, 错误的传递了字符串
     */
    @ExceptionHandler(value = NumberFormatException.class)
    @ResponseBody
    public Result myNumberFormatExceptionHandler(NumberFormatException e) {
        return Result.failed(415, "请求参数格式错误");
    }


    /**
     * 捕获未知异常
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result exceptionHandler(Exception e) {
        e.printStackTrace();
        return Result.failed(0, "some system exception");
    }


}

package com.su.aop;

import com.su.pojo.Article;
import com.su.pojo.Operation;
import com.su.service.*;
import com.su.utils.JsonUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class OperationLog {

    private final ArticleService as;
    private final CommentService cs;
    private final LetterService ls;
    private final TagService ts;
    private final OperationService os;

    @Autowired
    public OperationLog(ArticleService as, CommentService cs, OperationService os, LetterService ls, TagService ts) {
        this.as = as;
        this.cs = cs;
        this.os = os;
        this.ls = ls;
        this.ts = ts;
    }


    // 指定切入点, 切入 delete
    @Pointcut("execution(* com.su.service.*.delete (..))")
    public void doDelete() {
    }

    // 指定切入点, 切入 insert
    @Pointcut("execution(* com.su.service.*.insert (..))")
    public void doInsert() {
    }

    // 指定切入点, 切入 update
    @Pointcut("execution(* com.su.service.*.update (..))")
    public void doUpdate() {
    }


    // 目前只有文章需要修改
    @Before(value = "doUpdate()")
    public void doBeforeUpdate(JoinPoint joinPoint) {
        String className = joinPoint.getTarget().getClass().getName();  // 类名; com.su.service.ArticleService
        Article o = (Article) joinPoint.getArgs()[0];       // 参数
        String table = className.substring(15, className.indexOf("Service")).toLowerCase();   // 表名: article
        Operation operation = new Operation();
        operation.setTable(table);
        operation.setType("update");
        try {
            operation.setMessage(JsonUtil.objectToJson(as.findById(o.getId())));
            os.insert(operation);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Before(value = "doDelete()")
    public void doBeforeDelete(JoinPoint joinPoint) {
        String className = joinPoint.getTarget().getClass().getName();  // 类名; com.su.service.ArticleService
        Object o = joinPoint.getArgs()[0];       // 参数: 85
        String table = className.substring(15, className.indexOf("Service")).toLowerCase();   // 表名: article
        Operation operation = new Operation();
        operation.setTable(table);
        operation.setType("delete");
        try {
            // 判断需要哪个service
            switch (table) {
                case "article":
                    operation.setMessage(JsonUtil.objectToJson(as.findById(Integer.valueOf(o.toString()))));
                    break;
                case "comment":
                    operation.setMessage(JsonUtil.objectToJson(cs.findById(Integer.valueOf(o.toString()))));
                    break;
                case "letter":
                    operation.setMessage(JsonUtil.objectToJson(ls.findById(Integer.valueOf(o.toString()))));
                    break;
                case "tag":
                    operation.setMessage(JsonUtil.objectToJson(ts.findById(Integer.valueOf(o.toString()))));
                    break;
                default:
            }
            os.insert(operation);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

package com.su.controller.back;


import com.su.pojo.Result;
import com.su.service.SignInLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class SignLogController {

    @Autowired
    private SignInLogService ss;


    @RequestMapping("/sign-in-logs/{page}")
    public Result list(@PathVariable Integer page) throws Exception {
        return Result.success(ss.list(page, 20));
    }


}

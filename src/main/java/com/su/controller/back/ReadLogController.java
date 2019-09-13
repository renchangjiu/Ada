package com.su.controller.back;

import com.su.pojo.Result;
import com.su.service.ReadLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/admin")
public class ReadLogController {

    @Autowired
    private ReadLogService rs;

    @RequestMapping("/read-logs/{page}")
    public Result list(@PathVariable("page") Integer page) throws Exception {
        return Result.success(rs.list(page, 10));
    }


}

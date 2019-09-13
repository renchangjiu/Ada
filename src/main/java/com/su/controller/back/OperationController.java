package com.su.controller.back;

import com.su.pojo.Result;
import com.su.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class OperationController {

    @Autowired
    private OperationService os;

    @RequestMapping("/operations/{page}")
    public Result list(@PathVariable Integer page) throws Exception {
        return Result.success(os.list(page, 20));
    }

}

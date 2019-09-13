package com.su.controller.front;


import com.su.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import com.su.pojo.Result;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author su
 */
@RestController("frontEndTagController")
@RequestMapping("/api")
public class TagController {

    @Autowired
    private TagService ts;


}

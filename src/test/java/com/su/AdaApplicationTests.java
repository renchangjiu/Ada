package com.su;

import com.su.service.ArticleService;
import com.su.utils.Log;
import com.su.utils.RedisOperator;
import com.su.utils.StringUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

@Async
@RunWith(SpringRunner.class)
@SpringBootTest
public class AdaApplicationTests {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private RedisOperator redis;

    @Value("${personal.article.image.path}")
    private String path;

    @Autowired
    private ArticleService articleService;


    @Test
    public void test() throws Exception {
    }

}

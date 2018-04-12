package com.xiao.redis;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.Map;

/**
 * 测试spring boot 中 data - redis
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class StringRedisTest {

//    @Autowired
    private StringRedisTemplate template;

    public  void setKey(String key,String value){
        StringRedisTemplate template = new StringRedisTemplate();
        ValueOperations<String, String> ops = template.opsForValue();
        ops.set(key,value);
    }

//    public void stringRedis(){
//        redisTemplate.opsForCluster()
//    }
}

package com.xiaoming.sell.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * 视频不好使，自己参照网上构建
 */
//@Configuration
public class RedisConfig {

//    @Bean
//    @ConditionalOnMissingBean(name = "redisTemplate")
//    public RedisTemplate<Object,Object> redisTemplate(
//            RedisConnectionFactory redisConnectionFactory){
//        RedisTemplate<Object,Object> template = new RedisTemplate<Object,Object>();
//        template.setConnectionFactory(redisConnectionFactory);
//        return template;
//    }
//
//    @Bean
//    @ConditionalOnMissingBean(StringRedisTemplate.class)
//    public StringRedisTemplate stringRedisTemplate(
//            RedisConnectionFactory redisConnectionFactory){
//              StringRedisTemplate template = new StringRedisTemplate();
//              template.setConnectionFactory(redisConnectionFactory);
//              return template;
//    }
}

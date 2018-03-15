package com.xiaoming.sell;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class LoggerTest {
    //1.引入日志文件
//    private final Logger logger = LoggerFactory.getLogger(LoggerTest.class);
    //2.使用注解方式
    //2.2 IDEA安装插件 http://blog.csdn.net/zhglance/article/details/54931430
    //2.3 mavne中引入lombok 类上面直接引入@Slf4j  方法中 直接调用log.info就可以
    @Test
    public void test1(){
        //1.通常方法
//        logger.debug("debug..");
//        logger.info("info...");
//        logger.error("error...");

        //2.注解方法
        log.debug("debug..");
        log.info("info...");
        log.error("error...");
        log.warn("warn...");

        //3.日志中输出变量
        String name = "name";
        String password = "123";
        log.info("name:{},password:{}",name,password);

    }
}

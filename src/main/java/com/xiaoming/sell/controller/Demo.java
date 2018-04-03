package com.xiaoming.sell.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@EnableAutoConfiguration
public class Demo {

    @RequestMapping("/")
    @ResponseBody
    String name(){
        return "hello";
    }

    public static void main(String[] args) {
        SpringApplication.run(Demo.class,args);
    }

}

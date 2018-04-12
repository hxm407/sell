package com.xiao.redis;

import lombok.Data;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * jedis测试
 */
@RunWith(SpringRunner.class)
public class JedisDemo1 {
//    /**
//     * 单实例测试
//     */
//    @Test
//    public void demo1(){
//        //1.设置IP地址和端口
//        Jedis jedis = new Jedis("10.19.128.36",6379);
//        //2.保存数据
//        jedis.set("name","xiao123");
//        String value = jedis.get("name");
//        System.out.println(value);
//        //4.释放资源
//        jedis.close();
//    }
//
//
//    /**
//     * 连接池
//     */
//    @Test
//    public void demo2(){
//        //获取链接的对象
//        JedisPoolConfig config = new JedisPoolConfig();
//        //设置对打连接数
//        config.setMaxTotal(30);
//        //设置最大空闲连接数
//        config.setMaxIdle(10);
//
//        //获取连接池
//        JedisPool jedisPool = new JedisPool(config,"10.19.128.36",6379);
//
//        //获取黑心对象
//        Jedis jedis =null;
//        try {
//            //通过链接池获取链接
//            jedis = jedisPool.getResource();
//            // 设置数据
//            jedis.set("name","张三");
//            //获取数据
//            String value = jedis.get("name");
//            System.out.println(value);
//
//            jedis.lpush("mylist5","1","2","3","a","");
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }finally {
//            //释放连接时
//            if(jedis !=null){
//                jedis.close();
//            }
//            if(jedisPool!=null){
//                jedisPool.close();
//            }
//        }
//    }
}

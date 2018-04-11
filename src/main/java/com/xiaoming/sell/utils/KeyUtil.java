package com.xiaoming.sell.utils;

import java.util.Random;

/**
 * 生成索引，ID
 */
public class KeyUtil {
    /**
     * 生成唯一主键
     * <p>格式：时间+随机数</p>

     * @return
     */
    public static synchronized String getUniqueKey() {
        Random random = new Random();

        Integer number = random.nextInt(900000) + 100000;
        return System.currentTimeMillis() + String.valueOf(number);
    }
}

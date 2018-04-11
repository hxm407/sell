package com.xiaoming.sell.utils;

import com.xiaoming.sell.enums.CodeEnum;

/**
 * 抽象出 传入code 返回对应值
 */
public class EnumUtil {
    public static <T extends CodeEnum> T getByCode(Integer code, Class<T> enumClass) {
        for (T each: enumClass.getEnumConstants()) {
            if (code.equals(each.getCode())) {
                return each;
            }
        }
        return null;
    }
}

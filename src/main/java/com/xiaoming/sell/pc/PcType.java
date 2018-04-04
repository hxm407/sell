package com.xiaoming.sell.pc;

import lombok.Getter;

@Getter
public enum PcType {
    ZR(0,"自如"),
    AJK(1,"安居客");

    private Integer code;
    private String msg;

    PcType(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}

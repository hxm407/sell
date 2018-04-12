package com.xiaoming.sell.exception;

import com.xiaoming.sell.enums.ResultEnum;
import lombok.Getter;

@Getter
public class SellException extends RuntimeException{
    private  Integer code;

    public SellException() {
    }
    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }
    public SellException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}

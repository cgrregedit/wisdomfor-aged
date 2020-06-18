package com.cgr.lesson.exception;

import com.cgr.lesson.exception.code.BaseResponseCode;


/**
 * @ Author     ：cgr
 * @ Date       ：Created in 12:11 2020-04-30
 * @ Description：
 * @ Modified By：
 */
public class BusinessException extends RuntimeException{
    /**
     *  异常 code
     */
    private final int code;

    /**
     *  异常提示
     */
    public final String defaultMessage;

    public BusinessException(int code, String defaultMessage) {
        super(defaultMessage);
        this.code = code;
        this.defaultMessage = defaultMessage;
    }

    public BusinessException(BaseResponseCode baseResponseCode){
        this(baseResponseCode.getCode(), baseResponseCode.getMsg());
    }
    public int getCode() {
        return code;
    }

    public String getDefaultMessage() {
        return defaultMessage;
    }
}

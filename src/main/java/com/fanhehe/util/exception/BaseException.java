package com.fanhehe.util.exception;

import com.fanhehe.util.constant.response.IBaseResponse;

// 无上报必要
// 异常信息用户可见
public class BaseException extends RuntimeException {

    private int code;
    private String message;

    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    public String getSuperMessage() {
        return super.getMessage();
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    BaseException() {
        super();
    }

    BaseException(IBaseResponse type) {
        this(type.getCode(), type.getMessage());
    }

    BaseException(int code, String message) {
        super(message);
        this.code = code;
    }

    BaseException(String message, int code) {
        this(code, message);
    }

    BaseException(String message) {
        this(500, message);
    }

    BaseException(int code) {
        this(code, "网络异常");
    }
}

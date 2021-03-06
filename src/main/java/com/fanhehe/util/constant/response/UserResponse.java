package com.fanhehe.util.constant.response;

public enum UserResponse implements IBaseResponse {

    SUCCESS(0, ""),
    PARAM(400, "参数错误"),
    ERROR(500, "内部异常");

    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    UserResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }
}

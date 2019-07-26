package com.fanhehe.util.constant.response;

public enum MessageResponse implements IBaseResponse {

    SUCCESS(0, ""),
    PARAM(400, "参数错误"),
    ERROR(500, "内部异常"),

    INVAID_EMAIL(4000, "非合法邮箱"),
    EMPTY_PARAMS(4001, "必要参数不能为空"),
    CAPTCHA_WRONG(4002, "验证码不正确"),
    CAPTCHA_EXPIRED(4002, "验证码已失效"),

    CAPTCHA_NEED_WAIT(5002, "验证码邮件已发送，请稍等"),
    USER_BIND_NONE_EMAIL(5003, "用户未绑定邮箱账号"),
    SEND_MESSAGE_FAILED(5004, "发送邮件失败"),
    SEND_MESSAGE_SENDING(5005, "消息发送中"),
    SEND_MESSAGE_FINISHED(5006, "消息已发成功"),
    SEND_MESSAGE_UPDATE_ROW_NOT_ONE(5007, "更新行数不为1"),
    SEND_MESSAGE_NOT_SUPPORT(5008, "邮箱不支持本方法");

    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    MessageResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }
}

package com.fanhehe.util.result;

import javax.annotation.Resource;
import com.fanhehe.util.constant.response.IBaseResponse;

@Resource
public class InvokeResult<T> extends AbstractResult<T> {

    public static <T> IResult<T> success() {
        return success(null);
    }

    public static <T> IResult<T> failure(String message) {
        return failure(message, 500, null);
    }

    public static <T> IResult<T> failure(String message, int code) {
        return failure(message, code, null);
    }

    public static <T> IResult<T> failure(IBaseResponse type) {
        return failure(type.getMessage(), type.getCode(), null);
    }

    public static <T> IResult<T> failure(IBaseResponse type, T data) {
        return failure(type.getMessage(), type.getCode(), data);
    }

    public static <T> IResult<T> success(T data) {
        IResult<T> result = new InvokeResult<>();
        result.setData(data);
        return result;
    }

    public static<T> IResult<T> failure(String message, int code, T data) {
        IResult<T> result = new InvokeResult<>();
        result.setCode(code);
        result.setData(data);
        result.setMessage(message);
        return result;
    }
}

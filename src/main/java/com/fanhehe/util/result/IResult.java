package com.fanhehe.util.result;

import java.io.Serializable;

public interface IResult<T> extends Serializable {

    /**
     * 判断调用是否成功
     * @return 成功 true 失败 false
     */
    boolean isSuccess();

    /**
     * 获取负载信息
     * @return 负载信息
     */
    T getData();

    /**
     * 设置负载信息
     * @param data 设置负载信息
     */
    void setData(T data);
    void setCode(int code);
    void setMessage(String message);

    default int getCode() {
        return 0;
    }

    default String getMessage() {
        return "";
    }

    default boolean isFailure() {
        return !isSuccess();
    }
}

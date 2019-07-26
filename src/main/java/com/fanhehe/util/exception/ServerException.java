package com.fanhehe.util.exception;

import com.fanhehe.util.exception.protocol.ReportProtocol;
import com.fanhehe.util.exception.protocol.MessageProtocol;

// 需要上报
// 异常信息用户不可见
public class ServerException extends BaseException implements ReportProtocol, MessageProtocol {
    @Override
    public void report(int reportId) {}

    @Override
    public void replaceMessage(String newMessage) {
        this.setMessage(newMessage);
    }
}

package com.fanhehe.util.exception.protocol;

public interface ReportProtocol {
    void report(int reportId);

    default void report(int reportId, ReportProtocol reportProtocol) {
        if (reportProtocol == null) {
            report(reportId);
        }

        reportProtocol.report(reportId);
    }
}

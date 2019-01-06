package com.pricepopulator.model;

import com.pricepopulator.exception.Error;

import java.io.Serializable;

public class ErrorResponse implements Serializable {

    private Error.Severity severity;
    private ErrorDetail errorDetail;

    public static class ErrorDetail{
        private String message;
        private String code;

        public ErrorDetail(String message, String code) {
            this.message = message;
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }
    }

    public ErrorResponse(Error.Severity severity, ErrorDetail errorDetail) {
        this.severity = severity;
        this.errorDetail = errorDetail;
    }

    public Error.Severity getSeverity() {
        return severity;
    }

    public void setSeverity(Error.Severity severity) {
        this.severity = severity;
    }

    public ErrorDetail getErrorDetail() {
        return errorDetail;
    }

    public void setErrorDetail(ErrorDetail errorDetail) {
        this.errorDetail = errorDetail;
    }
}

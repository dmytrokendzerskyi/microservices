package com.pricepopulator.exception;

import com.pricepopulator.model.ErrorResponse;

public class ErrorBuilder {

    public ErrorResponse buildErrorResponse(String errorMessage, String errorCode, Error.Severity severity){
        ErrorResponse.ErrorDetail errorDetail = new ErrorResponse.ErrorDetail(errorMessage,errorCode);
        return new ErrorResponse(severity, errorDetail);
    }

}

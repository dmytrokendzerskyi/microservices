package com.pricepopulator.controller;

import com.pricepopulator.exception.Error;
import com.pricepopulator.exception.ErrorBuilder;
import com.pricepopulator.exception.PriceNotFoundException;
import com.pricepopulator.exception.PriceNotFoundWarning;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

@ControllerAdvice
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class PriceControllerAdvice {

    @Autowired
    private ErrorBuilder errorBuilder;

    @ExceptionHandler(PriceNotFoundException.class)
    public ResponseEntity handleNotFoundError(final PriceNotFoundException e){
        return new ResponseEntity(errorBuilder.buildErrorResponse(e.getMessage(),
                Error.Code.NOT_FOUND_ERROR_CODE, Error.Severity.ERROR), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PriceNotFoundWarning.class)
    public ResponseEntity handleNotFoundWarn(final PriceNotFoundWarning e){
        return new ResponseEntity(errorBuilder.buildErrorResponse(e.getMessage(),
                Error.Code.NOT_FOUND_ERROR_CODE, Error.Severity.WARN), HttpStatus.OK);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleError(final Exception e){
        return new ResponseEntity(errorBuilder.buildErrorResponse(e.getMessage(),
                Error.Code.ERROR_CODE, Error.Severity.ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}

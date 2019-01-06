package com.pricepopulator.exception;

public class Error {

    public interface ErrorMessage {
        String NOT_FOUND_ERROR_MESSAGE = "Price not found by id = ";
        String ERROR_MESSAGE = "internal server error";
    }

    public interface Code {
        String NOT_FOUND_ERROR_CODE = "010";
        String ERROR_CODE = "013";
    }

    public enum Severity{
        ERROR,WARN
    }
}

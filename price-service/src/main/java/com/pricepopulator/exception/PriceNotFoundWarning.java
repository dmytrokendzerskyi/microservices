package com.pricepopulator.exception;

public class PriceNotFoundWarning extends RuntimeException {
    public PriceNotFoundWarning(String message) {
        super(message);
    }
}

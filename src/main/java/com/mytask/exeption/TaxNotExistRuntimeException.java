package com.mytask.exeption;

public class TaxNotExistRuntimeException extends RuntimeException {
    public TaxNotExistRuntimeException(String message) {
        super(message);
    }
}

package com.mytask.exeption;

public class TaxNotExistRuntimeException extends MyRuntimeException {
    public TaxNotExistRuntimeException(String message) {
        super(message);
    }
}

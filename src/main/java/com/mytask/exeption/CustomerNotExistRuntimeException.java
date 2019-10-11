package com.mytask.exeption;

public class CustomerNotExistRuntimeException extends MyRuntimeException {
    public CustomerNotExistRuntimeException(String message) {
        super(message);
    }
}

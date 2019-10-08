package com.mytask.exeption;

public class CustomerNotExistRuntimeException extends RuntimeException {
    public CustomerNotExistRuntimeException(String message) {
        super(message);
    }
}

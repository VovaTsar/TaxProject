package com.mytask.exeption;

public class UserNotExistRuntimeException extends RuntimeException {
    public UserNotExistRuntimeException(String message) {
        super(message);
    }
}

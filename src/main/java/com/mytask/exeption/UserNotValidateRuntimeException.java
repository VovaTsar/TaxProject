package com.mytask.exeption;

public class UserNotValidateRuntimeException extends RuntimeException {
    public UserNotValidateRuntimeException(String message) {
        super(message);
    }
}

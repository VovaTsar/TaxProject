package com.mytask.exeption;

public class TaxActionWithNullRuntimeExeption extends RuntimeException {
    public TaxActionWithNullRuntimeExeption(String message) {
        super(message);
    }
}

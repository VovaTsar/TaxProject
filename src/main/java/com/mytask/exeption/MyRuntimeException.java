package com.mytask.exeption;

import org.apache.log4j.Logger;

public class MyRuntimeException extends RuntimeException {
    private static final Logger LOGGER = Logger.getLogger(MyRuntimeException.class);
    public MyRuntimeException(String message) {
        System.out.println(message);
        LOGGER.error(message);
    }
}

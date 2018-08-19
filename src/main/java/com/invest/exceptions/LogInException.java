package com.invest.exceptions;

public class LogInException extends NumberFormatException {

    private static String LOG_IN_EXCEPTION = "Wrong login or password";

    public LogInException() {
    }

    @Override
    public String getMessage() {
        return LOG_IN_EXCEPTION;
    }

}

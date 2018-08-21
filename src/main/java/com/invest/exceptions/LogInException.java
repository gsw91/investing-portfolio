package com.invest.exceptions;

public class LogInException extends NumberFormatException {

    public LogInException() {
    }

    @Override
    public String getMessage() {
        return "Wrong login or password";
    }

}

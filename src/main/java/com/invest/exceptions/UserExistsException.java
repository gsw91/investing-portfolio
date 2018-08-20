package com.invest.exceptions;

public class UserExistsException extends Exception {

    public static String EMAIL_FORBIDDEN = "This email has already been used";
    public static String USERNAME_EXISTS = "This username has already been used";

    public UserExistsException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

}
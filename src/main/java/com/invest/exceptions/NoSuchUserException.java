package com.invest.exceptions;

public class NoSuchUserException extends Exception{

    private static String NO_SUCH_USER = "No user has been found";

    public NoSuchUserException() {
    }

    @Override
    public String getMessage() {
        return NO_SUCH_USER;
    }

}

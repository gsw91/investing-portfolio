package com.invest.exceptions;

public class UserExistsException extends Exception {

    public static String EMAIL_FORBIDDEN = "This email has already been used";
    public static String USERNAME_EXISTS = "This username has already been used";
    public static String NO_SUCH_USER = "No such user was found";
    public static String LOGIN_SIGN_UP = "The login should contain more than 3 letters";
    public static String PASSWORD_SIGN_UP = "The password should contain more than 3 letters";
    public static String EMAIL_SIGN_UP = "Please entry a correct email";

    public UserExistsException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

}
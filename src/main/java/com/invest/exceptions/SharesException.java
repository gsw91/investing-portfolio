package com.invest.exceptions;

public class SharesException extends Exception {

    public static String UPDATING_QUOTATIONS_FAILED = "Updating quotations failed";
    public static String MARKET_PRICE_EXCEPTION = "No share has been found";

    public SharesException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

}

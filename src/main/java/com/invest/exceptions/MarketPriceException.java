package com.invest.exceptions;

public class MarketPriceException extends Exception {

    private static String NO_SUCH_ELEMENT = "No market price has been found";

    public MarketPriceException() {
    }

    @Override
    public String getMessage() {
        return NO_SUCH_ELEMENT;
    }
}

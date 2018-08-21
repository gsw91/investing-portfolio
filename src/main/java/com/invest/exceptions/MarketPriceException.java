package com.invest.exceptions;

public class MarketPriceException extends Exception {

    public static String UPDATING_QUOTATIONS_FAILED = "Updating quotations failed";
    public static String MARKET_PRICE_EXCEPTION = "No market price has been found";

    public MarketPriceException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

}

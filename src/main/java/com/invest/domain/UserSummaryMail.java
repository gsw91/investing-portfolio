package com.invest.domain;

import java.math.BigDecimal;

public final class UserSummaryMail {

    private final String instrument;
    private final long quantity;
    private final double purchasePrice;
    private final BigDecimal investedCapital;
    private final double currentPrice;
    private final BigDecimal valuation;
    private final BigDecimal result;

    public UserSummaryMail(final String instrument,final long quantity,final double purchasePrice, final double currentPrice) {
        this.instrument = instrument;
        this.quantity = quantity;
        this.purchasePrice = purchasePrice;
        this.investedCapital = calculateInvestedCapital();
        this.currentPrice = currentPrice;
        this.valuation = calculateValuation();
        this.result = calculateResult();
    }

    private BigDecimal calculateResult() {
        BigDecimal result = BigDecimal.valueOf((currentPrice - purchasePrice)*quantity);
        return result.divide(BigDecimal.ONE, 2, 2);
    }

    private BigDecimal calculateValuation() {
        BigDecimal valuation = BigDecimal.valueOf(currentPrice*quantity);
        return valuation.divide(BigDecimal.ONE, 2, 2);
    }

    private BigDecimal calculateInvestedCapital() {
        BigDecimal valuation = BigDecimal.valueOf(purchasePrice*quantity);
        return valuation.divide(BigDecimal.ONE, 2, 2);
    }

    public String getInstrument() {
        return instrument;
    }

    public long getQuantity() {
        return quantity;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public BigDecimal getInvestedCapital() {
        return investedCapital;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public BigDecimal getValuation() {
        return valuation;
    }

    public BigDecimal getResult() {
        return result;
    }



}

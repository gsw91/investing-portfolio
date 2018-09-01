package com.invest.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class StatisticsDto {

    private Long id;
    private Long user;
    private String instrumentName;
    private BigDecimal buyingPrice;
    private LocalDate buyingDate;
    private Long quantity;
    private BigDecimal sellingPrice;
    private LocalDate sellingDate;
    private BigDecimal result;
    private BigDecimal returnRate;
    private Long duration;

    public StatisticsDto() {
    }

    public StatisticsDto(Long user, String instrumentName, BigDecimal buyingPrice, LocalDate buyingDate, Long quantity, BigDecimal sellingPrice, LocalDate sellingDate) {
        this.user = user;
        this.instrumentName = instrumentName;
        this.buyingPrice = buyingPrice;
        this.buyingDate = buyingDate;
        this.quantity = quantity;
        this.sellingPrice = sellingPrice;
        this.sellingDate = sellingDate;
    }

    public StatisticsDto(Long id, Long user, String instrumentName, BigDecimal buyingPrice, LocalDate buyingDate,
                         Long quantity, BigDecimal sellingPrice, LocalDate sellingDate, BigDecimal result, BigDecimal returnRate, Long duration) {
        this.id = id;
        this.user = user;
        this.instrumentName = instrumentName;
        this.buyingPrice = buyingPrice;
        this.buyingDate = buyingDate;
        this.quantity = quantity;
        this.sellingPrice = sellingPrice;
        this.sellingDate = sellingDate;
        this.result = result;
        this.returnRate = returnRate;
        this.duration = duration;
    }

    public Long getId() {
        return id;
    }

    public Long getUser() {
        return user;
    }

    public String getInstrumentName() {
        return instrumentName;
    }

    public BigDecimal getBuyingPrice() {
        return buyingPrice;
    }

    public LocalDate getBuyingDate() {
        return buyingDate;
    }

    public Long getQuantity() {
        return quantity;
    }

    public BigDecimal getSellingPrice() {
        return sellingPrice;
    }

    public LocalDate getSellingDate() {
        return sellingDate;
    }

    public BigDecimal getResult() {
        return result;
    }

    public BigDecimal getReturnRate() {
        return returnRate;
    }

    public Long getDuration() {
        return duration;
    }

}

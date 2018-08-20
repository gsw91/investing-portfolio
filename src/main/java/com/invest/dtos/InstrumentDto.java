package com.invest.dtos;

import com.invest.domain.MarketPrice;

import java.time.LocalDate;

public class InstrumentDto {

    private Long id;
    private Long userId;
    private Long quantity;
    private MarketPrice marketPrice;
    private Double buyingPrice;
    private LocalDate buyingDate;

    public InstrumentDto() {
    }

    public InstrumentDto(Long id, Long userId, Long quantity, MarketPrice marketPrice, Double buyingPrice, LocalDate buyingDate) {
        this.id = id;
        this.userId = userId;
        this.quantity = quantity;
        this.marketPrice = marketPrice;
        this.buyingPrice = buyingPrice;
        this.buyingDate = buyingDate;
    }

    public InstrumentDto(Long userId, Long quantity, MarketPrice marketPrice, Double buyingPrice, LocalDate buyingDate) {
        this.userId = userId;
        this.quantity = quantity;
        this.marketPrice = marketPrice;
        this.buyingPrice = buyingPrice;
        this.buyingDate = buyingDate;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getQuantity() {
        return quantity;
    }

    public MarketPrice getMarketPrice() {
        return marketPrice;
    }

    public Double getBuyingPrice() {
        return buyingPrice;
    }

    public LocalDate getBuyingDate() {
        return buyingDate;
    }
}

package com.invest.dtos;

import com.invest.domain.MarketPrice;
import com.invest.domain.User;

import java.time.LocalDate;

public class InstrumentDto {

    private Long id;
    private User user;
    private MarketPrice marketPrice;
    private Double buyingPrice;
    private LocalDate buyingDate;

    public InstrumentDto(Long id, User user, MarketPrice marketPrice, Double buyingPrice, LocalDate buyingDate) {
        this.id = id;
        this.user = user;
        this.marketPrice = marketPrice;
        this.buyingPrice = buyingPrice;
        this.buyingDate = buyingDate;
    }

    public InstrumentDto(User user, MarketPrice marketPrice, Double buyingPrice, LocalDate buyingDate) {
        this.user = user;
        this.marketPrice = marketPrice;
        this.buyingPrice = buyingPrice;
        this.buyingDate = buyingDate;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
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

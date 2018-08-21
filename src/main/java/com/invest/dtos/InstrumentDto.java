package com.invest.dtos;

import java.time.LocalDate;

public class InstrumentDto {

    private Long userId;
    private Long quantity;
    private String sharesIndex;
    private Double buyingPrice;
    private LocalDate buyingDate;

    public InstrumentDto() {
    }

    public InstrumentDto(Long userId, Long quantity, String sharesIndex, Double buyingPrice, LocalDate buyingDate) {
        this.userId = userId;
        this.quantity = quantity;
        this.sharesIndex = sharesIndex;
        this.buyingPrice = buyingPrice;
        this.buyingDate = buyingDate;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getQuantity() {
        return quantity;
    }

    public String getSharesIndex() {
        return sharesIndex;
    }

    public Double getBuyingPrice() {
        return buyingPrice;
    }

    public LocalDate getBuyingDate() {
        return buyingDate;
    }

    @Override
    public String toString() {
        return "InstrumentDto{" +
                ", userId=" + userId +
                ", quantity=" + quantity +
                ", sharesIndex='" + sharesIndex + '\'' +
                ", buyingPrice=" + buyingPrice +
                ", buyingDate=" + buyingDate +
                '}';
    }

}
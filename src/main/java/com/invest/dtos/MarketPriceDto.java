package com.invest.dtos;

import java.time.LocalDateTime;

public class MarketPriceDto {

    private Long id;
    private String index;
    private Double price;
    private LocalDateTime serverActualization;
    private LocalDateTime applicationActualization;

    public MarketPriceDto() {
    }

    public MarketPriceDto(Long id, String index, Double price, LocalDateTime serverActualization, LocalDateTime applicationActualization) {
        this.id = id;
        this.index = index;
        this.price = price;
        this.serverActualization = serverActualization;
        this.applicationActualization = applicationActualization;
    }

    public Long getId() {
        return id;
    }

    public String getIndex() {
        return index;
    }

    public Double getPrice() {
        return price;
    }

    public LocalDateTime getServerActualization() {
        return serverActualization;
    }

    public LocalDateTime getApplicationActualization() {
        return applicationActualization;
    }
}

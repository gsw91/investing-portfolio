package com.invest.dtos;

import com.invest.domain.*;
import java.time.LocalDateTime;
import java.util.*;

public class MarketPriceDto {

    private Long id;
    private String index;
    private List<Instrument> instruments = new ArrayList<>();
    private List<User> users = new ArrayList<>();
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

    public MarketPriceDto(Long id, String index, List<Instrument> instruments, List<User> users, Double price, LocalDateTime serverActualization, LocalDateTime applicationActualization) {
        this.id = id;
        this.index = index;
        this.instruments = instruments;
        this.users = users;
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

    public List<Instrument> getInstruments() {
        return instruments;
    }

    public List<User> getUsers() {
        return users;
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

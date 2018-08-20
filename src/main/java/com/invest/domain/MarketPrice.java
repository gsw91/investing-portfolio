package com.invest.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CURRENT_MARKET_PRICES")
@Access(AccessType.PROPERTY)
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "index")
@JsonIdentityReference(alwaysAsId=true)
public class MarketPrice {

    private Long id;
    private String index;
    private List<Instrument> instruments = new ArrayList<>();
    private Double price;
    private LocalDateTime serverActualization;
    private LocalDateTime applicationActualization;

    public MarketPrice() {
    }

    public MarketPrice(Long id, String index, Double price, LocalDateTime serverActualization, LocalDateTime applicationActualization) {
        this.id = id;
        this.index = index;
        this.price = price;
        this.serverActualization = serverActualization;
        this.applicationActualization = applicationActualization;
    }

    public MarketPrice(Long id, String index, List<Instrument> instruments, Double price, LocalDateTime serverActualization, LocalDateTime applicationActualization) {
        this.id = id;
        this.index = index;
        this.instruments = instruments;
        this.price = price;
        this.serverActualization = serverActualization;
        this.applicationActualization = applicationActualization;
    }

    @Id
    @Column(name = "MARKET_ID")
    public Long getId() {
        return id;
    }

    @OneToMany(targetEntity = Instrument.class, mappedBy = "marketPrice", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    public List<Instrument> getInstruments() {
        return instruments;
    }

    @Column(name = "INSTR_NAME", nullable = false)
    public String getIndex() {
        return index;
    }

    @Column(name = "CURRENT_PRICE", nullable = false)
    public Double getPrice() {
        return price;
    }

    @Column(name = "STOCK_UPDATING", nullable = false)
    public LocalDateTime getServerActualization() {
        return serverActualization;
    }

    @Column(name = "APP_UPDATING", nullable = false)
    public LocalDateTime getApplicationActualization() {
        return applicationActualization;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public void setPrice(Double price) {
        this.price = price;
    }

    public void setServerActualization(LocalDateTime serverActualization) {
        this.serverActualization = serverActualization;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public void setInstruments(List<Instrument> instruments) {
        this.instruments = instruments;
    }

    public void setApplicationActualization(LocalDateTime applicationActualization) {
        this.applicationActualization = applicationActualization;
    }

}

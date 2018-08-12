package com.invest.tables;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CURRENT_MARKET_PRICES")
@Access(AccessType.PROPERTY)
public class MarketPrice {

    private Long id;
    private String index;
    private List<Instrument> instruments = new ArrayList<>();
    private List<User> users = new ArrayList<>();
    private Double price;
    private LocalDateTime actualization;

    public MarketPrice() {
    }

    public MarketPrice(String index, Double price, LocalDateTime actualization) {
        this.index = index;
        this.price = price;
        this.actualization = actualization;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MARKET_ID", nullable = false, unique = true)
    public Long getId() {
        return id;
    }

    @OneToMany(targetEntity = Instrument.class, mappedBy = "marketPrice", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public List<Instrument> getInstruments() {
        return instruments;
    }

    @Column(name = "INSTR_NAME", nullable = false)
    public String getIndex() {
        return index;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "JOIN_USERS_INSTRUMENTS",
            joinColumns = {
                    @JoinColumn(name = "MARKET_ID", referencedColumnName = "MARKET_ID")},
            inverseJoinColumns = {
                    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")}
    )
    public List<User> getUsers() {
        return users;
    }

    @Column(name = "CURRENT_PRICE", nullable = false)
    public Double getPrice() {
        return price;
    }

    @Column(name = "UPDATED", nullable = false)
    public LocalDateTime getActualization() {
        return actualization;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setActualization(LocalDateTime actualization) {
        this.actualization = actualization;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public void setInstruments(List<Instrument> instruments) {
        this.instruments = instruments;
    }

}

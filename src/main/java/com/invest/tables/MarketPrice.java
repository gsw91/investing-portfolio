package com.invest.tables;

import javax.persistence.*;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CURRENT_MARKET_PRICES")
@Access(AccessType.PROPERTY)
public class MarketPrice {

    private Long id;
    private Instrument instrument;
    private List<User> users = new ArrayList<>();
    private Double price;
    private Time actualization;

    public MarketPrice() {
    }

    public MarketPrice(Instrument instrument, Double price, Time actualization) {
        this.instrument = instrument;
        this.price = price;
        this.actualization = actualization;
    }

    @Id
    @GeneratedValue
    @Column(name = "MARKET_ID", nullable = false, unique = true)
    public Long getId() {
        return id;
    }

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "INSTR_ID", nullable = false)
    public Instrument getInstrument() {
        return instrument;
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
    public Time getActualization() {
        return actualization;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setInstrument(Instrument instrument) {
        this.instrument = instrument;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setActualization(Time actualization) {
        this.actualization = actualization;
    }
}

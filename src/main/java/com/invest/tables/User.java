package com.invest.tables;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "USERS")
@Access(AccessType.PROPERTY)
public class User {
    private Long id;
    private String login;
    private String password;
    private String email;
    private List<Instrument> instruments = new ArrayList<>();
    private List<Statistics> statistics = new ArrayList<>();
    private List<MarketPrice> marketPrices = new ArrayList<>();

    public User() {
    }

    public User(String login, String password, String email) {
        this.login = login;
        this.password = password;
        this.email = email;
    }

    @Id
    @GeneratedValue
    @Column(name = "USER_ID", nullable = false, unique = true)
    public Long getId() {
        return id;
    }

    @Column(name = "LOGIN", nullable = false)
    public String getLogin() {
        return login;
    }

    @Column(name = "PASSWORD", nullable = false)
    public String getPassword() {
        return password;
    }

    @Column(name = "EMAIL", nullable = false)
    public String getEmail() {
        return email;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @OneToMany(targetEntity = Instrument.class, mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public List<Instrument> getInstruments() {
        return instruments;
    }

    public void setInstruments(List<Instrument> instruments) {
        this.instruments = instruments;
    }

    @OneToMany(targetEntity = Statistics.class, mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public List<Statistics> getStatistics() {
        return statistics;
    }

    public void setStatistics(List<Statistics> statistics) {
        this.statistics = statistics;
    }

    @ManyToMany(targetEntity = MarketPrice.class, mappedBy = "users", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public List<MarketPrice> getMarketPrices() {
        return marketPrices;
    }

    public void setMarketPrices(List<MarketPrice> marketPrices) {
        this.marketPrices = marketPrices;
    }
}
package com.invest.dtos;

import com.invest.domain.Instrument;
import com.invest.domain.MarketPrice;
import com.invest.domain.Statistics;

import java.util.ArrayList;
import java.util.List;

public class UserDto {

    private Long id;
    private String login;
    private String password;
    private String email;
    private List<Instrument> instruments = new ArrayList<>();
    private List<Statistics> statistics = new ArrayList<>();
    private List<MarketPrice> marketPrices = new ArrayList<>();

    public UserDto() {
    }

    public UserDto(Long id, String login, String password, String email) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.email = email;
    }

    public UserDto(Long id, String login, String password, String email, List<Instrument> instruments, List<Statistics> statistics, List<MarketPrice> marketPrices) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.email = email;
        this.instruments = instruments;
        this.statistics = statistics;
        this.marketPrices = marketPrices;
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public List<Instrument> getInstruments() {
        return instruments;
    }

    public List<Statistics> getStatistics() {
        return statistics;
    }

    public List<MarketPrice> getMarketPrices() {
        return marketPrices;
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

    public void setInstruments(List<Instrument> instruments) {
        this.instruments = instruments;
    }

    public void setStatistics(List<Statistics> statistics) {
        this.statistics = statistics;
    }

    public void setMarketPrices(List<MarketPrice> marketPrices) {
        this.marketPrices = marketPrices;
    }

}

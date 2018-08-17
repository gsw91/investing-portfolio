package com.invest.domain;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "INSTRUMENTS")
@Access(AccessType.PROPERTY)
public class Instrument {
    private Long id;
    private User user;
    private MarketPrice marketPrice;
    private Double buyingPrice;
    private LocalDate buyingDate;

    public Instrument() {
    }

    public Instrument(User user, MarketPrice marketPrice, double buyingPrice, LocalDate buyingDate) {
        this.user = user;
        this.marketPrice = marketPrice;
        this.buyingPrice = buyingPrice;
        this.buyingDate = buyingDate;
    }

    public Instrument(Long id, User user, MarketPrice marketPrice, Double buyingPrice, LocalDate buyingDate) {
        this.id = id;
        this.user = user;
        this.marketPrice = marketPrice;
        this.buyingPrice = buyingPrice;
        this.buyingDate = buyingDate;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    public Long getId() {
        return id;
    }

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    public User getUser() {
        return user;
    }

    @ManyToOne
    @JoinColumn(name = "MARKET_ID", nullable = false)
    public MarketPrice getMarketPrice() {
        return marketPrice;
    }

    @Column(name = "BUYING_PRICE", nullable = false)
    public double getBuyingPrice() {
        return buyingPrice;
    }

    @Column(name = "BUYING_DATE", nullable = false)
    public LocalDate getBuyingDate() {
        return buyingDate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setMarketPrice(MarketPrice marketPrice) {
        this.marketPrice = marketPrice;
    }

    public void setBuyingPrice(Double buyingPrice) {
        this.buyingPrice = buyingPrice;
    }

    public void setBuyingDate(LocalDate buyingDate) {
        this.buyingDate = buyingDate;
    }

}

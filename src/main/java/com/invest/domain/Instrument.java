package com.invest.domain;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "INSTRUMENTS")
@Access(AccessType.PROPERTY)
public class Instrument {
    private Long id;
    private User user;
    private Long quantity;
    private MarketPrice marketPrice;
    private Double buyingPrice;
    private LocalDate buyingDate;

    public Instrument() {
    }

    public Instrument(Long id, MarketPrice marketPrice, Long quantity, Double buyingPrice, LocalDate buyingDate) {
        this.id = id;
        this.marketPrice = marketPrice;
        this.quantity = quantity;
        this.buyingPrice = buyingPrice;
        this.buyingDate = buyingDate;
    }

    public Instrument(User user, MarketPrice marketPrice, Long quantity, double buyingPrice, LocalDate buyingDate) {
        this.user = user;
        this.marketPrice = marketPrice;
        this.quantity = quantity;
        this.buyingPrice = buyingPrice;
        this.buyingDate = buyingDate;
    }

    public Instrument(Long id, User user,Long quantity, MarketPrice marketPrice, Double buyingPrice, LocalDate buyingDate) {
        this.id = id;
        this.user = user;
        this.quantity = quantity;
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

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID")
    public User getUser() {
        return user;
    }

    @Column(name = "QUANTITY")
    public Long getQuantity() {
        return quantity;
    }

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "MARKET_ID")
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

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

}

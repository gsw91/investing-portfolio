package com.invest.tables;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "STATISTICS")
@Access(AccessType.PROPERTY)
public class Statistics {

    private Long id;
    private User user;
    private String instrumentName;
    private BigDecimal buyingPrice;
    private LocalDate buyingDate;
    private Long quantity;
    private BigDecimal sellingPrice;
    private LocalDate sellingDate;
    private BigDecimal result;
    private BigDecimal returnRate;
    private Long duration;

    public Statistics() {
    }

    public Statistics(User user, String instrumentName, BigDecimal buyingPrice, LocalDate buyingDate, BigDecimal sellingPrice, LocalDate sellingDate, Long quantity) {
        this.user = user;
        this.instrumentName = instrumentName;
        this.buyingPrice = buyingPrice;
        this.buyingDate = buyingDate;
        this.sellingPrice = sellingPrice;
        this.sellingDate = sellingDate;
        this.quantity = quantity;
        this.result = (sellingPrice.subtract(buyingPrice)).setScale(2, BigDecimal.ROUND_HALF_UP).multiply(BigDecimal.valueOf(quantity));
        this.returnRate = (sellingPrice.subtract(buyingPrice)).setScale(2, BigDecimal.ROUND_HALF_UP).divide(buyingPrice, 4, BigDecimal.ROUND_HALF_UP).multiply(BigDecimal.valueOf(100L));
        this.duration = sellingDate.toEpochDay()-buyingDate.toEpochDay();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "STAT_ID", nullable = false)
    public Long getId() {
        return id;
    }

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    public User getUser() {
        return user;
    }

    @Column(name = "INSTR_NAME", nullable = false)
    public String getInstrumentName() {
        return instrumentName;
    }

    @Column(name = "BUYING_PRICE", nullable = false)
    public BigDecimal getBuyingPrice() {
        return buyingPrice;
    }

    @Column(name = "BUYING_DATE", nullable = false)
    public LocalDate getBuyingDate() {
        return buyingDate;
    }

    @Column(name = "SELLING_PRICE", nullable = false)
    public BigDecimal getSellingPrice() {
        return sellingPrice;
    }

    @Column(name = "SELLING_DATE", nullable = false)
    public LocalDate getSellingDate() {
        return sellingDate;
    }

    @Column(name = "QUANTITY", nullable = false)
    public Long getQuantity() {
        return quantity;
    }

    @Column(name = "INVEST_RETURN", nullable = false)
    public BigDecimal getResult() {
        return result;
    }

    @Column(name = "RETURN_RATE", nullable = false)
    public BigDecimal getReturnRate() {
        return returnRate;
    }

    @Column(name = "DURATION_IN_DAYS", nullable = false)
    public Long getDuration() {
        return duration;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setInstrumentName(String instrumentName) {
        this.instrumentName = instrumentName;
    }

    public void setBuyingPrice(BigDecimal buyingPrice) {
        this.buyingPrice = buyingPrice;
    }

    public void setBuyingDate(LocalDate buyingDate) {
        this.buyingDate = buyingDate;
    }

    public void setSellingPrice(BigDecimal sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public void setSellingDate(LocalDate sellingDate) {
        this.sellingDate = sellingDate;
    }

    public void setResult(BigDecimal result) {
        this.result = result;
    }

    public void setReturnRate(BigDecimal returnRate) {
        this.returnRate = returnRate;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

}

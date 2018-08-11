package com.invest.tables;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "STATISTICS")
@Access(AccessType.PROPERTY)
public class Statistics {

    private Long id;
    private User user;
    private String instrumentName;
    private Double buyingPrice;
    private Date buyingDate;
    private Double sellingPrice;
    private Date sellingDate;
    private Double result;
    private Double returnRate;
    private Long duration;

    public Statistics() {
    }

    public Statistics(User user, String instrumentName, Double buyingPrice, Date buyingDate, Double sellingPrice, Date sellingDate) {
        this.user = user;
        this.instrumentName = instrumentName;
        this.buyingPrice = buyingPrice;
        this.buyingDate = buyingDate;
        this.sellingPrice = sellingPrice;
        this.sellingDate = sellingDate;
        this.result = sellingPrice-buyingPrice;
        this.returnRate = result/buyingPrice;
        this.duration = sellingDate.getTime()-buyingDate.getTime();
    }

    @Id
    @GeneratedValue
    @Column(name = "STAT_ID", nullable = false, unique = true)
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
    public Double getBuyingPrice() {
        return buyingPrice;
    }

    @Column(name = "BUYING_DATE", nullable = false)
    public Date getBuyingDate() {
        return buyingDate;
    }

    @Column(name = "SELLING_PRICE", nullable = false)
    public Double getSellingPrice() {
        return sellingPrice;
    }

    @Column(name = "SELLING_DATE", nullable = false)
    public Date getSellingDate() {
        return sellingDate;
    }

    @Column(name = "INVEST_RETURN", nullable = false)
    public Double getResult() {
        return result;
    }

    @Column(name = "RETURN_RATE", nullable = false)
    public Double getReturnRate() {
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

    public void setBuyingPrice(Double buyingPrice) {
        this.buyingPrice = buyingPrice;
    }

    public void setBuyingDate(Date buyingDate) {
        this.buyingDate = buyingDate;
    }

    public void setSellingPrice(Double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public void setSellingDate(Date sellingDate) {
        this.sellingDate = sellingDate;
    }

    public void setResult(Double result) {
        this.result = result;
    }

    public void setReturnRate(Double returnRate) {
        this.returnRate = returnRate;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }
}

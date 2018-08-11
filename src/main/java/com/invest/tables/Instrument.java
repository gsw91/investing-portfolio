package com.invest.tables;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "INSTRUMENTS")
@Access(AccessType.PROPERTY)
public class Instrument {
    private Long id;
    private User user;
    private String instrumentName;
    private Double buyingPrice;
    private Date buyingDate;

    public Instrument() {
    }

    public Instrument(User user, String instrumentName, double buyingPrice, Date buyingDate) {
        this.user = user;
        this.instrumentName = instrumentName;
        this.buyingPrice = buyingPrice;
        this.buyingDate = buyingDate;
    }

    @Id
    @GeneratedValue
    @Column(name = "INSTR_ID", nullable = false, unique = true)
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
    public double getBuyingPrice() {
        return buyingPrice;
    }

    @Column(name = "BUYING_DATE", nullable = false)
    public Date getBuyingDate() {
        return buyingDate;
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

    public void setBuyingPrice(double buyingPrice) {
        this.buyingPrice = buyingPrice;
    }

    public void setBuyingDate(Date buyingDate) {
        this.buyingDate = buyingDate;
    }

}

package com.invest.quotations;

import java.time.LocalDateTime;
import java.util.Objects;

public class Share {

    private String index;
    private Double price;
    private LocalDateTime serverActualization;
    private LocalDateTime applicationActualization;

    public Share(){}

    public Share(String index, Double price, LocalDateTime serverActualization, LocalDateTime applicationActualization) {
        this.index = index;
        this.price = price;
        this.serverActualization = serverActualization;
        this.applicationActualization = applicationActualization;
    }

    public String getIndex() {
        return index;
    }

    public Double getPrice() {
        return price;
    }

    public LocalDateTime getServerActualization() {
        return serverActualization;
    }

    public LocalDateTime getApplicationActualization() {
        return applicationActualization;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Share)) return false;
        Share that = (Share) o;
        return Objects.equals(getIndex(), that.getIndex()) &&
                Objects.equals(getPrice(), that.getPrice()) &&
                Objects.equals(getServerActualization(), that.getServerActualization()) &&
                Objects.equals(getApplicationActualization(), that.getApplicationActualization());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIndex(), getPrice(), getServerActualization(), getApplicationActualization());
    }

    @Override
    public String toString() {
        return "Share{" +
                "index='" + index + '\'' +
                ", price=" + price +
                ", serverActualization=" + serverActualization +
                ", applicationActualization=" + applicationActualization +
                '}';
    }

}

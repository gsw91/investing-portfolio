package com.invest.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class QuotationsConfig {

    @Value("${info.quots.website}")
    private String quotationsPage;
    @Value("${info.guots.element.share}")
    private String sharesNames;
    @Value("${info.guots.element.price}")
    private String sharesPrices;
    @Value("${info.guots.element.actualization}")
    private String actualization;
    @Value("${info.quots.browser.userAgent}")
    private String userAgent;

    public String getQuotationsPage() {
        return quotationsPage;
    }

    public String getSharesNames() {
        return sharesNames;
    }

    public String getSharesPrices() {
        return sharesPrices;
    }

    public String getActualization() {
        return actualization;
    }

    public String getUserAgent() {
        return userAgent;
    }

}

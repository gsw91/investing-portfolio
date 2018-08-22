package com.invest.quotations;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class SharesMap {

    private static Logger LOGGER = Logger.getLogger(SharesMap.class);

    @Autowired
    private QuotationConnectingMap quotationConnectingMap;

    private Map<String, Share> marketPriceMap = new HashMap<>();

    @Bean
    public Map<String, Share> getMarketPriceMap() {
        Long startTime = System.currentTimeMillis();
        LOGGER.info("Preparing map quotations ");
        updateQuotations();
        Long endTime = System.currentTimeMillis();
        LOGGER.info("Quotations ready, size: " + marketPriceMap.size() +", it took " + (endTime-startTime) + " ms");
        return marketPriceMap;
    }

    @Scheduled(cron = "0 0/05 9-17 * * MON-FRI")
    public void updateQuotations () {
        Long startTime = System.currentTimeMillis();
        LOGGER.info("Start updating  map quotations ");
        quotationConnectingMap.updateQuotations(marketPriceMap);
        Long endTime = System.currentTimeMillis();
        LOGGER.info("Quotations updated, size: " + marketPriceMap.size() +", it took " + (endTime-startTime) + " ms");
    }

}

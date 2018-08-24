package com.invest.quotations;

import com.invest.scheduleTasks.QuotationsUpdating;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class SharesMap {

    private static Logger LOGGER = Logger.getLogger(SharesMap.class);

    @Autowired
    private QuotationsUpdating quotationsUpdating;

    public static Map<String, Share> marketPriceMap = new HashMap<>();

    @Bean
    public Map<String, Share> getMarketPriceMap() {
        Long startTime = System.currentTimeMillis();
        LOGGER.info("Preparing map quotations ");
        quotationsUpdating.updateQuotations();
        Long endTime = System.currentTimeMillis();
        LOGGER.info("Quotations ready, size: " + marketPriceMap.size() +", it took " + (endTime-startTime) + " ms");
        return marketPriceMap;
    }

}

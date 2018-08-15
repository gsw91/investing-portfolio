package com.invest.scheduleTasks;

import com.invest.services.MarketPriceService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class QuotationsUpdating {

    private static final Logger LOGGER = Logger.getLogger(QuotationsUpdating.class);

    @Autowired
    private MarketPriceService marketPriceService;

    @Scheduled(cron = "0 */15 9-18 * * MON-FRI")
    public void updateQuotations() {
        LOGGER.info("Starting updating quotations...");
        boolean isUpdated = marketPriceService.updatePrices();
        if(isUpdated) {
            LOGGER.info("Current quotations updated");
        } else  {
            LOGGER.warn("Updating quotations failed");
        }
    }

}

package com.invest.scheduledTasks;

import com.invest.quotations.QuotationConnecting;
import com.invest.quotations.Share;
import com.invest.quotations.SharesMap;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class QuotationsUpdating {

    private static Logger LOGGER = Logger.getLogger(QuotationsUpdating.class);

    @Autowired
    private QuotationConnecting quotationConnecting;

    @Scheduled(cron = "0 0/05 9-17 * * MON-FRI")
    public void updateQuotations () {
        Long startTime = System.currentTimeMillis();
        LOGGER.info("Start updating  map quotations ");
        Map<String, Share> map = quotationConnecting.updateQuotations(SharesMap.marketPriceMap);
        Long endTime = System.currentTimeMillis();
        if (map.size() > 0) {
            LOGGER.info("Quotations updated, size: " + SharesMap.marketPriceMap.size() + ", it took " + (endTime - startTime) + " ms");
        }
    }

}

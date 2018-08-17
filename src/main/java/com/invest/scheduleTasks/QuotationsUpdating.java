package com.invest.scheduleTasks;

import com.invest.domain.MarketPrice;
import com.invest.dtos.MarketPriceDto;
import com.invest.quotations.QuotationConnecting;
import com.invest.services.MarketPriceService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class QuotationsUpdating {

    private static final Logger LOGGER = Logger.getLogger(QuotationsUpdating.class);

    @Autowired
    private QuotationConnecting quotationConnecting;

    @Autowired
    private MarketPriceService marketPriceService;

    @Scheduled(cron = "0 0/05 9-17 * * MON-FRI")
    public void updateQuotations() {
        LOGGER.info("Starting updating quotations...");
        long start = System.currentTimeMillis();
        MarketPriceDto beforeUpdateFirst = marketPriceService.findMarketPrice(0L);
        MarketPriceDto beforeUpdateLast = marketPriceService.findMarketPrice(354L);
        List<MarketPriceDto> listDto = marketPriceService.updatePrices(quotationConnecting.updateQuotations());
        MarketPriceDto afterUpdateFirst = marketPriceService.findMarketPrice(0L);
        MarketPriceDto afterUpdateLast = marketPriceService.findMarketPrice(354L);

        if (!beforeUpdateFirst.equals(afterUpdateFirst) && !beforeUpdateLast.equals(afterUpdateLast)) {
            long stop = System.currentTimeMillis();
            LOGGER.info("Current quotations updated in " + (stop-start)/1000 + " sec");
        } else {
            LOGGER.warn("Updating quotations failed");
        }
    }

}

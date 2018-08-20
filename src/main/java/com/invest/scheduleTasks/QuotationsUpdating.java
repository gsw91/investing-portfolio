package com.invest.scheduleTasks;

import com.invest.dtos.MarketPriceDto;
import com.invest.exceptions.MarketPriceException;
import com.invest.mappers.MarketPriceMapper;
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
    private MarketPriceService service;

    @Autowired
    private MarketPriceMapper mapper;

    @Scheduled(cron = "0 0/05 9-17 * * MON-FRI")
    public void updateQuotations() {
        LOGGER.info("Starting updating quotations...");
        long start = System.currentTimeMillis();

        try {

            MarketPriceDto beforeUpdateFirst = mapper.mapperToDto(service.findMarketPrice("11BIT"));
            MarketPriceDto beforeUpdateLast = mapper.mapperToDto(service.findMarketPrice("ZUE"));
            List<MarketPriceDto> listDto = quotationConnecting.updateQuotations();
            service.updatePrices(mapper.mapperToListDomain(listDto));
            MarketPriceDto afterUpdateFirst = mapper.mapperToDto(service.findMarketPrice("11BIT"));
            MarketPriceDto afterUpdateLast = mapper.mapperToDto(service.findMarketPrice("ZUE"));

            if (!beforeUpdateFirst.equals(afterUpdateFirst) && !beforeUpdateLast.equals(afterUpdateLast)) {
                long stop = System.currentTimeMillis();
                LOGGER.info("Current quotations updated in " + (stop-start)/1000 + " sec");
            } else {
                LOGGER.warn("Updating quotations failed");
            }

        } catch (MarketPriceException e) {
            LOGGER.error(e.getMessage());
        }

    }

}

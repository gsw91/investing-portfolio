package com.invest.services;

import com.invest.quotations.QuotationConnecting;
import com.invest.repositories.MarketPriceDao;
import com.invest.tables.MarketPrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MarketPriceService {

    @Autowired
    private MarketPriceDao marketPriceDao;

    @Autowired
    private QuotationConnecting quotationConnecting;


    public List<MarketPrice> updatePrices() {
        List<MarketPrice> currentQuotations = quotationConnecting.updateQuotations();

        for (MarketPrice currentPrice : currentQuotations) {
            marketPriceDao.save(currentPrice);
        }

        return currentQuotations;
    }

    public List<MarketPrice> findMarketPrices() {
        return marketPriceDao.findAll();
    }

}

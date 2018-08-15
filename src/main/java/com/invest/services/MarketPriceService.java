package com.invest.services;

import com.invest.dtos.MarketPriceDto;
import com.invest.mappers.MarketPriceMapper;
import com.invest.quotations.QuotationConnecting;
import com.invest.repositories.MarketPriceDao;
import com.invest.domain.MarketPrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarketPriceService {

    @Autowired
    private MarketPriceMapper marketPriceMapper;

    @Autowired
    private MarketPriceDao marketPriceDao;

    @Autowired
    private QuotationConnecting quotationConnecting;

    public boolean updatePrices() {
        List<MarketPriceDto> currentQuotations = quotationConnecting.updateQuotations();
        List<MarketPrice> quotationsToDb = marketPriceMapper.mapperToListDomain(currentQuotations);
        if (currentQuotations.size()>0) {
            for (long i=0; i<quotationsToDb.size(); i++) {
                if (marketPriceDao.existsById(i)) {
                    marketPriceDao.deleteById(i);
                    MarketPrice currentPrice = quotationsToDb.get((int) i);
                    marketPriceDao.save(currentPrice);
                }
            }
            return true;
        }
        return false;
    }

    public List<MarketPriceDto> findMarketPrices() {
        return marketPriceMapper.mapperToListDto(marketPriceDao.findAll());
    }

}

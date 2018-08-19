package com.invest.services;

import com.invest.domain.MarketPrice;
import com.invest.exceptions.MarketPriceException;
import com.invest.repositories.MarketPriceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarketPriceService {

    @Autowired
    private MarketPriceDao marketPriceDao;

    public List<MarketPrice> updatePrices(List<MarketPrice> list) {
        return marketPriceDao.saveAll(list);
    }

    public List<MarketPrice> getAll() {
        return marketPriceDao.findAll();
    }

    public MarketPrice findMarketPrice(Long id) throws MarketPriceException {
        if (marketPriceDao.findById(id).isPresent()) {
            return marketPriceDao.findById(id).get();
        } else {
            throw new MarketPriceException();
        }
    }

}

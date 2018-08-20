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

    public MarketPrice findMarketPrice(String index) throws MarketPriceException {
        boolean isExisting = marketPriceDao.findAll().stream()
                .anyMatch(t->t.getIndex().equals(index));
        if (isExisting) {
            return marketPriceDao.findAll().stream()
                    .filter(t->t.getIndex().equals(index))
                    .findFirst().get();
        } else {
            throw new MarketPriceException();
        }
    }

}

package com.invest.services;

import com.invest.dtos.MarketPriceDto;
import com.invest.mappers.MarketPriceMapper;
import com.invest.repositories.MarketPriceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarketPriceService {

    @Autowired
    private MarketPriceMapper mapper;

    @Autowired
    private MarketPriceDao marketPriceDao;

    public List<MarketPriceDto> updatePrices(List<MarketPriceDto> listDto) {
        return mapper.mapperToListDto(marketPriceDao.saveAll(mapper.mapperToListDomain(listDto)));
    }

    public List<MarketPriceDto> findMarketPrices() {
        return mapper.mapperToListDto(marketPriceDao.findAll());
    }

    public MarketPriceDto findMarketPrice(Long id) {
        if (marketPriceDao.findById(id).isPresent()) {
            return mapper.mapperToDto(marketPriceDao.findById(id).get());
        } else {
            return new MarketPriceDto();
        }
    }

}

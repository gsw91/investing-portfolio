package com.invest.mappers;

import com.invest.domain.MarketPrice;
import com.invest.dtos.MarketPriceDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MarketPriceMapper implements BasicMapper<MarketPrice, MarketPriceDto> {

    @Override
    public void accept(Object o) {}

    @Override
    public MarketPrice mapperToDomain(MarketPriceDto marketPriceDto) {
        MarketPrice marketPrice = new MarketPrice(
                marketPriceDto.getId(),
                marketPriceDto.getIndex(),
                marketPriceDto.getPrice(),
                marketPriceDto.getServerActualization(),
                marketPriceDto.getApplicationActualization()
        );
        marketPrice.setInstruments(marketPriceDto.getInstruments());
        marketPrice.setUsers(marketPriceDto.getUsers());
        return marketPrice;
    }

    @Override
    public MarketPriceDto mapperToDto(MarketPrice marketPrice) {
        MarketPriceDto marketPriceDto = new MarketPriceDto(
                marketPrice.getId(),
                marketPrice.getIndex(),
                marketPrice.getPrice(),
                marketPrice.getServerActualization(),
                marketPrice.getApplicationActualization()
        );
        marketPriceDto.setInstruments(marketPrice.getInstruments());
        marketPriceDto.setUsers(marketPrice.getUsers());
        return marketPriceDto;
    }

    @Override
    public List<MarketPrice> mapperToListDomain(List<MarketPriceDto> listDomain) {
        List<MarketPrice> marketPrices = new ArrayList<>();
        listDomain.stream()
                .map(this::mapperToDomain)
                .forEach(marketPrices::add);
        return marketPrices;
    }

    @Override
    public List<MarketPriceDto> mapperToListDto(List<MarketPrice> listDto) {
        List<MarketPriceDto> marketPrices = new ArrayList<>();
        listDto.stream()
                .map(this::mapperToDto)
                .forEach(marketPrices::add);
        return marketPrices;
    }
}

package com.invest.mappers;

import com.invest.domain.MarketPrice;
import com.invest.dtos.MarketPriceDto;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MarketPriceMapper implements BasicMapper<MarketPrice, MarketPriceDto> {

    @Override
    public void accept(Object o) {

    }

    @Override
    public MarketPrice mapperToDomain(MarketPriceDto marketPriceDto) {
        return new MarketPrice(
                marketPriceDto.getId(),
                marketPriceDto.getIndex(),
                marketPriceDto.getPrice(),
                marketPriceDto.getServerActualization(),
                marketPriceDto.getApplicationActualization()
        );
    }

    @Override
    public MarketPriceDto mapperToDto(MarketPrice marketPrice) {
        return new MarketPriceDto(
                marketPrice.getId(),
                marketPrice.getIndex(),
                marketPrice.getPrice(),
                marketPrice.getServerActualization(),
                marketPrice.getApplicationActualization()
        );
    }

    @Override
    public List<MarketPrice> mapperToListDomain(List<MarketPriceDto> listDomain) {
        return listDomain.stream()
                .map(this::mapperToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<MarketPriceDto> mapperToListDto(List<MarketPrice> listDto) {
        return listDto.stream()
                .map(this::mapperToDto)
                .collect(Collectors.toList());
    }

}

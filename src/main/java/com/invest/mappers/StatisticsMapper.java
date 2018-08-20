package com.invest.mappers;

import com.invest.domain.Statistics;
import com.invest.dtos.StatisticsDto;
import java.util.List;
import java.util.stream.Collectors;

public class StatisticsMapper implements BasicMapper<Statistics, StatisticsDto> {

    @Override
    public void accept(Object o) {

    }

    @Override
    public Statistics mapperToDomain(StatisticsDto statisticsDto) {
         return new Statistics(
                statisticsDto.getId(),
                statisticsDto.getUser(),
                statisticsDto.getInstrumentName(),
                statisticsDto.getBuyingPrice(),
                statisticsDto.getBuyingDate(),
                statisticsDto.getQuantity(),
                statisticsDto.getSellingPrice(),
                statisticsDto.getSellingDate(),
                statisticsDto.getResult(),
                statisticsDto.getReturnRate(),
                statisticsDto.getDuration());
    }

    @Override
    public List<Statistics> mapperToListDomain(List<StatisticsDto> listDto) {
        return listDto.stream()
                .map(this::mapperToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public StatisticsDto mapperToDto(Statistics statistics) {
        return new StatisticsDto(
                statistics.getId(),
                statistics.getUser(),
                statistics.getInstrumentName(),
                statistics.getBuyingPrice(),
                statistics.getBuyingDate(),
                statistics.getQuantity(),
                statistics.getSellingPrice(),
                statistics.getSellingDate(),
                statistics.getResult(),
                statistics.getReturnRate(),
                statistics.getDuration());
    }

    @Override
    public List<StatisticsDto> mapperToListDto(List<Statistics> listDomain) {
        return listDomain.stream()
                .map(this::mapperToDto)
                .collect(Collectors.toList());
    }

}

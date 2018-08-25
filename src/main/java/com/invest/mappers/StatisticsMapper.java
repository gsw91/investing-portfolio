package com.invest.mappers;

import com.invest.domain.Statistics;
import com.invest.domain.User;
import com.invest.dtos.StatisticsDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StatisticsMapper implements BasicMapper<Statistics, StatisticsDto> {

    @Override
    public Statistics mapperToDomain(StatisticsDto statisticsDto) {
        return new Statistics(
                statisticsDto.getId(),
                new User(statisticsDto.getUser()),
                statisticsDto.getInstrumentName(),
                statisticsDto.getBuyingPrice(),
                statisticsDto.getBuyingDate(),
                statisticsDto.getSellingPrice(),
                statisticsDto.getSellingDate(),
                statisticsDto.getQuantity());
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
                statistics.getUser().getId(),
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

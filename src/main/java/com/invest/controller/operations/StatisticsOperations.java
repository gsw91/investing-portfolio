package com.invest.controller.operations;

import com.invest.dtos.InstrumentDto;
import com.invest.dtos.StatisticsDto;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class StatisticsOperations {

    public List<StatisticsDto> createStatisticsWhenAllSold(List<InstrumentDto> instruments, Long userId, String name, double price) {
        List<StatisticsDto> statisticsDtos = new ArrayList<>();
        for(InstrumentDto instrument: instruments) {

            StatisticsDto statisticsDto = new StatisticsDto(
                    userId,
                    name,
                    BigDecimal.valueOf(instrument.getBuyingPrice()),
                    instrument.getBuyingDate(),
                    instrument.getQuantity(),
                    BigDecimal.valueOf(price),
                    LocalDate.now());
            statisticsDtos.add(statisticsDto);
        }
        return statisticsDtos;
    }

    public StatisticsDto createStatisticsWhenMoreSold(Long userId, String name, int statisticsVariable, double price, List<InstrumentDto> instruments) {
        return new StatisticsDto(
                userId,
                name,
                BigDecimal.valueOf(instruments.get(statisticsVariable).getBuyingPrice()),
                instruments.get(statisticsVariable).getBuyingDate(),
                instruments.get(statisticsVariable).getQuantity(),
                BigDecimal.valueOf(price),
                LocalDate.now()
        );
    }

    public StatisticsDto createStatisticsWhenLessSold(Long userId, String name, int statisticsVariable, Long quantity, double price, List<InstrumentDto> instruments) {
        return new StatisticsDto(
                userId,
                name,
                BigDecimal.valueOf(instruments.get(statisticsVariable).getBuyingPrice()),
                instruments.get(statisticsVariable).getBuyingDate(),
                quantity,
                BigDecimal.valueOf(price),
                LocalDate.now()
        );
    }

}

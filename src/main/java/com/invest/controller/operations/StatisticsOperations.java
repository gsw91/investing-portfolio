package com.invest.controller.operations;

import com.invest.dtos.InstrumentDto;
import com.invest.dtos.StatisticsDto;
import com.invest.mappers.StatisticsMapper;
import com.invest.services.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class    StatisticsOperations {

    @Autowired
    private StatisticsService statisticsService;

    @Autowired
    private StatisticsMapper statisticsMapper;

    public void createStatisticsWhenAllSold(List<InstrumentDto> instruments, Long userId, String name, double price) {
        for(InstrumentDto instrument: instruments) {

            statisticsService.addNewStatistics(statisticsMapper.mapperToDomain(new StatisticsDto(
                    userId,
                    name,
                    BigDecimal.valueOf(instrument.getBuyingPrice()),
                    instrument.getBuyingDate(),
                    instrument.getQuantity(),
                    BigDecimal.valueOf(price),
                    LocalDate.now()
            )));
        }
    }

    public void createStatisticsWhenMoreSold(Long userId, String name, int statisticsVariable, double price, List<InstrumentDto> instruments) {
        statisticsService.addNewStatistics(
                statisticsMapper.mapperToDomain(
                        new StatisticsDto(
                                userId,
                                name,
                                BigDecimal.valueOf(instruments.get(statisticsVariable).getBuyingPrice()),
                                instruments.get(statisticsVariable).getBuyingDate(),
                                instruments.get(statisticsVariable).getQuantity(),
                                BigDecimal.valueOf(price),
                                LocalDate.now()
                        )
                )
        );
    }

    public void createStatisticsWhenLessSold(Long userId, String name, int statisticsVariable, Long quantity, double price, List<InstrumentDto> instruments) {
        statisticsService.addNewStatistics(
                statisticsMapper.mapperToDomain(
                        new StatisticsDto(
                                userId,
                                name,
                                BigDecimal.valueOf(instruments.get(statisticsVariable).getBuyingPrice()),
                                instruments.get(statisticsVariable).getBuyingDate(),
                                quantity,
                                BigDecimal.valueOf(price),
                                LocalDate.now()
                        )
                )
        );
    }

}

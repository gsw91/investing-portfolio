package com.invest.controller.operations;

import com.invest.domain.Statistics;
import com.invest.domain.User;
import com.invest.dtos.InstrumentDto;
import com.invest.dtos.StatisticsDto;
import com.invest.mappers.StatisticsMapper;
import com.invest.services.StatisticsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StatisticsOperationsTestSuite {

    @MockBean
    private StatisticsOperations statisticsOperations;

    @MockBean
    private StatisticsService statisticsService;

    @MockBean
    private StatisticsMapper statisticsMapper;

    @Test
    public void testCreateStatisticsWhenAllSold() {
        //given
        Long userId = 12L;
        String name = "COGNOR";
        List<InstrumentDto> list = new ArrayList<>();
        double sellingPrice = 2.20;
        list.add(new InstrumentDto(41L, userId, 1000L, name, 2.01, LocalDate.of(2018,8,2)));
        list.add(new InstrumentDto(44L, userId, 1000L, name, 2.20, LocalDate.of(2018,8,7)));
        list.add(new InstrumentDto(47L, userId, 1000L, name, 1.99, LocalDate.of(2018,8,11)));

        StatisticsDto statisticsDto1 = new StatisticsDto(userId, name, BigDecimal.valueOf(list.get(0).getBuyingPrice()), list.get(0).getBuyingDate(),
                list.get(0).getQuantity(), BigDecimal.valueOf(sellingPrice), LocalDate.now());

        StatisticsDto statisticsDto2 = new StatisticsDto(userId, name, BigDecimal.valueOf(list.get(1).getBuyingPrice()), list.get(1).getBuyingDate(),
                list.get(1).getQuantity(), BigDecimal.valueOf(sellingPrice), LocalDate.now());

        StatisticsDto statisticsDto3 = new StatisticsDto(userId, name, BigDecimal.valueOf(list.get(2).getBuyingPrice()), list.get(2).getBuyingDate(),
                list.get(2).getQuantity(), BigDecimal.valueOf(sellingPrice), LocalDate.now());

        Statistics statistics1 = new Statistics(4L, new User(userId), name, BigDecimal.valueOf(2.01), LocalDate.of(2018,8,2),
                BigDecimal.valueOf(sellingPrice), LocalDate.now(), 1000L);

        Statistics statistics2 = new Statistics(5L, new User(userId), name, BigDecimal.valueOf(2.20), LocalDate.of(2018,8,7),
                BigDecimal.valueOf(sellingPrice), LocalDate.now(), 1000L);

        Statistics statistics3 = new Statistics(6L, new User(userId), name, BigDecimal.valueOf(1.99), LocalDate.of(2018,8,11),
                BigDecimal.valueOf(sellingPrice), LocalDate.now(), 1000L);

        when(statisticsService.addNewStatistics(statistics1)).thenReturn(statistics1);
        when(statisticsService.addNewStatistics(statistics2)).thenReturn(statistics2);
        when(statisticsService.addNewStatistics(statistics3)).thenReturn(statistics3);
        when(statisticsMapper.mapperToDomain(statisticsDto1)).thenReturn(statistics1);
        when(statisticsMapper.mapperToDomain(statisticsDto2)).thenReturn(statistics2);
        when(statisticsMapper.mapperToDomain(statisticsDto3)).thenReturn(statistics3);
        //when
        statisticsOperations.createStatisticsWhenAllSold(list, userId, name, sellingPrice);
        //then
        verify(statisticsOperations, times(1)).createStatisticsWhenAllSold(list, userId, name, sellingPrice);
    }

    @Test
    public void testCreateStatisticsWhenMoreSold() {
        //given
        Long userId = 12L;
        String name = "COGNOR";
        List<InstrumentDto> list = new ArrayList<>();
        double sellingPrice = 2.20;
        list.add(new InstrumentDto(41L, userId, 1000L, name, 2.01, LocalDate.of(2018,8,2)));
        list.add(new InstrumentDto(44L, userId, 1000L, name, 2.20, LocalDate.of(2018,8,7)));
        list.add(new InstrumentDto(47L, userId, 1000L, name, 1.99, LocalDate.of(2018,8,11)));

        StatisticsDto statisticsDto1 = new StatisticsDto(userId, name, BigDecimal.valueOf(list.get(0).getBuyingPrice()), list.get(0).getBuyingDate(),
                list.get(0).getQuantity(), BigDecimal.valueOf(sellingPrice), LocalDate.now());

        StatisticsDto statisticsDto2 = new StatisticsDto(userId, name, BigDecimal.valueOf(list.get(1).getBuyingPrice()), list.get(1).getBuyingDate(),
                list.get(1).getQuantity(), BigDecimal.valueOf(sellingPrice), LocalDate.now());

        StatisticsDto statisticsDto3 = new StatisticsDto(userId, name, BigDecimal.valueOf(list.get(2).getBuyingPrice()), list.get(2).getBuyingDate(),
                list.get(2).getQuantity(), BigDecimal.valueOf(sellingPrice), LocalDate.now());

        Statistics statistics1 = new Statistics(4L, new User(userId), name, BigDecimal.valueOf(2.01), LocalDate.of(2018,8,2),
                BigDecimal.valueOf(sellingPrice), LocalDate.now(), 1000L);

        Statistics statistics2 = new Statistics(5L, new User(userId), name, BigDecimal.valueOf(2.20), LocalDate.of(2018,8,7),
                BigDecimal.valueOf(sellingPrice), LocalDate.now(), 1000L);

        Statistics statistics3 = new Statistics(6L, new User(userId), name, BigDecimal.valueOf(1.99), LocalDate.of(2018,8,11),
                BigDecimal.valueOf(sellingPrice), LocalDate.now(), 1000L);

        when(statisticsService.addNewStatistics(statistics1)).thenReturn(statistics1);
        when(statisticsService.addNewStatistics(statistics2)).thenReturn(statistics2);
        when(statisticsService.addNewStatistics(statistics3)).thenReturn(statistics3);
        when(statisticsMapper.mapperToDomain(statisticsDto1)).thenReturn(statistics1);
        when(statisticsMapper.mapperToDomain(statisticsDto2)).thenReturn(statistics2);
        when(statisticsMapper.mapperToDomain(statisticsDto3)).thenReturn(statistics3);
        //when
        statisticsOperations.createStatisticsWhenMoreSold(userId, name, 0, sellingPrice, list);
        statisticsOperations.createStatisticsWhenMoreSold(userId, name, 1, sellingPrice, list);
        statisticsOperations.createStatisticsWhenMoreSold(userId, name, 2, sellingPrice, list);
        //then
        verify(statisticsOperations, times(1)).createStatisticsWhenMoreSold(userId, name, 0, sellingPrice, list);
        verify(statisticsOperations, times(1)).createStatisticsWhenMoreSold(userId, name, 1, sellingPrice, list);
        verify(statisticsOperations, times(1)).createStatisticsWhenMoreSold(userId, name, 2, sellingPrice, list);
    }

    @Test
    public void testCreateStatisticsWhenLessSold() {
    //given
        Long userId = 12L;
        String name = "COGNOR";
        List<InstrumentDto> list = new ArrayList<>();
        double sellingPrice = 2.20;
        long quantity = 800;
        list.add(new InstrumentDto(41L, userId, 1000L, name, 2.01, LocalDate.of(2018,8,2)));

        StatisticsDto statisticsDto1 = new StatisticsDto(userId, name, BigDecimal.valueOf(list.get(0).getBuyingPrice()), list.get(0).getBuyingDate(),
                list.get(0).getQuantity(), BigDecimal.valueOf(sellingPrice), LocalDate.now());

        Statistics statistics1 = new Statistics(4L, new User(userId), name, BigDecimal.valueOf(2.01), LocalDate.of(2018,8,2),
                BigDecimal.valueOf(sellingPrice), LocalDate.now(), 1000L);

        when(statisticsService.addNewStatistics(statistics1)).thenReturn(statistics1);
        when(statisticsMapper.mapperToDomain(statisticsDto1)).thenReturn(statistics1);
        //when
        statisticsOperations.createStatisticsWhenLessSold(userId, name, 0, quantity,sellingPrice, list);
         //then
        verify(statisticsOperations, times(1)).createStatisticsWhenLessSold(userId, name, 0, quantity,sellingPrice, list);
    }

}

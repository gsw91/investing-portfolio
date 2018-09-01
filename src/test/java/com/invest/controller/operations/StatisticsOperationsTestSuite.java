package com.invest.controller.operations;

import com.invest.dtos.InstrumentDto;
import com.invest.dtos.StatisticsDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StatisticsOperationsTestSuite {

    @Autowired
    private StatisticsOperations statisticsOperations;

    @Test
    public void testCreateStatisticsWhenAllSold() {
        //given
        long userId = 12;
        String name = "COGNOR";
        List<InstrumentDto> list = new ArrayList<>();
        double sellingPrice = 2.20;
        list.add(new InstrumentDto(41L, userId, 1000L, name, 2.01, LocalDate.of(2018, 8, 2)));
        list.add(new InstrumentDto(44L, userId, 1000L, name, 2.20, LocalDate.of(2018, 8, 7)));
        list.add(new InstrumentDto(47L, userId, 1000L, name, 1.99, LocalDate.of(2018, 8, 11)));
        //when
        List<StatisticsDto> statisticsDtos = statisticsOperations.createStatisticsWhenAllSold(list, userId, name, sellingPrice);
        //then
        Assert.assertEquals(3, statisticsDtos.size());
        Assert.assertEquals(userId, statisticsDtos.get(0).getUser().longValue());
        Assert.assertEquals(name, statisticsDtos.get(1).getInstrumentName());
        Assert.assertEquals(1000L, statisticsDtos.get(2).getQuantity().longValue());
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
        //when
        StatisticsDto statisticsDto1 = statisticsOperations.createStatisticsWhenMoreSold(userId, name, 0, sellingPrice, list);
        StatisticsDto statisticsDto2 = statisticsOperations.createStatisticsWhenMoreSold(userId, name, 1, sellingPrice, list);
        StatisticsDto statisticsDto3 = statisticsOperations.createStatisticsWhenMoreSold(userId, name, 2, sellingPrice, list);
        //then
        Assert.assertNotNull(statisticsDto1);
        Assert.assertNotNull(statisticsDto2);
        Assert.assertNotNull(statisticsDto3);
        Assert.assertEquals(userId, statisticsDto1.getUser());
        Assert.assertEquals(1000L, statisticsDto2.getQuantity().longValue());
        Assert.assertEquals(1.99, statisticsDto3.getBuyingPrice().doubleValue(), 0.01);
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
        //when
        StatisticsDto statisticsDto = statisticsOperations.createStatisticsWhenLessSold(userId, name, 0, quantity,sellingPrice, list);
        //then
        Assert.assertNotNull(statisticsDto);
        Assert.assertEquals(userId, statisticsDto.getUser());
    }

}

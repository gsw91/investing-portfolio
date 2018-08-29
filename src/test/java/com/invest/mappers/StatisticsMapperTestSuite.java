package com.invest.mappers;

import com.invest.domain.Statistics;
import com.invest.domain.User;
import com.invest.dtos.StatisticsDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StatisticsMapperTestSuite {

    @Autowired
    private StatisticsMapper mapper;

    @Test
    public void shouldReturnStatistics() {
        //given
        StatisticsDto statisticsDto = new StatisticsDto(21L, 12L, "PKNORLEN", BigDecimal.valueOf(91.46), LocalDate.of(2018, 8, 20),
                1121L, BigDecimal.valueOf(100.01), LocalDate.of(2018, 8, 29), BigDecimal.valueOf(9584.55), BigDecimal.valueOf(9.3500), 9L);
        //when
        Statistics statistics = mapper.mapperToDomain(statisticsDto);
        //then
        Assert.assertEquals(21L, statistics.getId().longValue());
        Assert.assertEquals(12L, statistics.getUser().getId().longValue());
        Assert.assertEquals("PKNORLEN", statistics.getInstrumentName());
        Assert.assertEquals(BigDecimal.valueOf(91.46), statistics.getBuyingPrice());
        Assert.assertEquals(LocalDate.of(2018, 8, 20), statistics.getBuyingDate());
        Assert.assertEquals(1121L, statistics.getQuantity().longValue());
        Assert.assertEquals(BigDecimal.valueOf(100.01), statistics.getSellingPrice());
        Assert.assertEquals(LocalDate.of(2018, 8, 29), statistics.getSellingDate());
        Assert.assertEquals(BigDecimal.valueOf(9584.55), statistics.getResult());
        Assert.assertEquals(BigDecimal.valueOf(9.35), BigDecimal.valueOf(statistics.getReturnRate().doubleValue()));
        Assert.assertEquals(9L, statistics.getDuration().longValue());
    }

    @Test
    public void shouldReturnStatisticsDto() {
        Statistics statistics = new Statistics(21L, new User(12L), "PKNORLEN", BigDecimal.valueOf(91.46), LocalDate.of(2018, 8, 20),
                BigDecimal.valueOf(100.01), LocalDate.of(2018, 8, 29), 1121L);
        //when
        StatisticsDto statisticsDto = mapper.mapperToDto(statistics);
        //then
        Assert.assertEquals(21L, statisticsDto.getId().longValue());
        Assert.assertEquals(12L, statisticsDto.getUser().longValue());
        Assert.assertEquals("PKNORLEN", statisticsDto.getInstrumentName());
        Assert.assertEquals(BigDecimal.valueOf(91.46), statisticsDto.getBuyingPrice());
        Assert.assertEquals(LocalDate.of(2018, 8, 20), statisticsDto.getBuyingDate());
        Assert.assertEquals(1121L, statisticsDto.getQuantity().longValue());
        Assert.assertEquals(BigDecimal.valueOf(100.01), statisticsDto.getSellingPrice());
        Assert.assertEquals(LocalDate.of(2018, 8, 29), statisticsDto.getSellingDate());
        Assert.assertEquals(BigDecimal.valueOf(9584.55), statisticsDto.getResult());
        Assert.assertEquals(BigDecimal.valueOf(9.35), BigDecimal.valueOf(statisticsDto.getReturnRate().doubleValue()));
        Assert.assertEquals(9L, statisticsDto.getDuration().longValue());
    }

    @Test
    public void shouldReturnStatisticsList() {
        //given
        StatisticsDto statisticsDto1 = new StatisticsDto(21L, 12L, "PKNORLEN", BigDecimal.valueOf(91.46), LocalDate.of(2018, 8, 20),
                1121L, BigDecimal.valueOf(100.01), LocalDate.of(2018, 8, 29), BigDecimal.valueOf(9584.55), BigDecimal.valueOf(9.3500), 9L);
        StatisticsDto statisticsDto2 = new StatisticsDto(21L, 12L, "PKNORLEN", BigDecimal.valueOf(90.46), LocalDate.of(2018, 8, 20),
                911L, BigDecimal.valueOf(100.01), LocalDate.of(2018, 8, 29), BigDecimal.valueOf(9584.55), BigDecimal.valueOf(9.3500), 9L);
        StatisticsDto statisticsDto3 = new StatisticsDto(21L, 12L, "PKNORLEN", BigDecimal.valueOf(91.46), LocalDate.of(2018, 8, 20),
                2141L, BigDecimal.valueOf(100.01), LocalDate.of(2018, 8, 29), BigDecimal.valueOf(9584.55), BigDecimal.valueOf(9.3500), 9L);
        List<StatisticsDto> statisticsDtos = new ArrayList<>();
        statisticsDtos.add(statisticsDto1);
        statisticsDtos.add(statisticsDto2);
        statisticsDtos.add(statisticsDto3);
        //when
        List<Statistics> statistics = mapper.mapperToListDomain(statisticsDtos);
        //then
        Assert.assertEquals(3, statistics.size());
        Assert.assertEquals(BigDecimal.valueOf(9584.55), statistics.get(0).getResult());
        Assert.assertEquals(BigDecimal.valueOf(8700.05), statistics.get(1).getResult());
        Assert.assertEquals(BigDecimal.valueOf(18305.55), statistics.get(2).getResult());
    }

    @Test
    public void shouldReturnStatisticsDtoList() {
        //given
        Statistics statistics1 = new Statistics(21L, new User(12L), "PKNORLEN", BigDecimal.valueOf(91.46), LocalDate.of(2018, 8, 20),
                BigDecimal.valueOf(100.01), LocalDate.of(2018, 8, 29), 1121L);
        Statistics statistics2 = new Statistics(21L, new User(12L), "PKNORLEN", BigDecimal.valueOf(90.46), LocalDate.of(2018, 8, 20),
                BigDecimal.valueOf(100.01), LocalDate.of(2018, 8, 29), 911L);
        Statistics statistics3 = new Statistics(21L, new User(12L), "PKNORLEN", BigDecimal.valueOf(91.46), LocalDate.of(2018, 8, 20),
                BigDecimal.valueOf(100.01), LocalDate.of(2018, 8, 29), 2141L);
        List<Statistics> statistics = new ArrayList<>();
        statistics.add(statistics1);
        statistics.add(statistics2);
        statistics.add(statistics3);
        //when
        List<StatisticsDto> statisticsDtos = mapper.mapperToListDto(statistics);
        //then
        Assert.assertEquals(3, statistics.size());
        Assert.assertEquals(BigDecimal.valueOf(9584.55), statisticsDtos.get(0).getResult());
        Assert.assertEquals(BigDecimal.valueOf(8700.05), statisticsDtos.get(1).getResult());
        Assert.assertEquals(BigDecimal.valueOf(18305.55), statisticsDtos.get(2).getResult());
    }

}

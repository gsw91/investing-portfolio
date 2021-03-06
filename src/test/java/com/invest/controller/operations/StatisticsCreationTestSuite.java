package com.invest.controller.operations;

import com.invest.domain.Statistics;
import com.invest.domain.User;
import com.invest.dtos.StatisticsDto;
import com.invest.mappers.StatisticsMapper;
import com.invest.services.StatisticsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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
public class StatisticsCreationTestSuite {

    @Autowired
    private StatisticsCreation statisticsCreation;

    @MockBean
    private StatisticsService statisticsService;

    @MockBean
    private StatisticsMapper statisticsMapper;

    @Test
    public void testSaveAllStatistics() {
        //given
        List<StatisticsDto> statisticsDtos = new ArrayList<>();
        statisticsDtos.add(new StatisticsDto(1L, "COGNOR", BigDecimal.valueOf(2.22), LocalDate.of(2018,
                8,20), 3000L, BigDecimal.valueOf(2.30), LocalDate.of(2018, 8, 28)));
        statisticsDtos.add(new StatisticsDto(1L, "COGNOR", BigDecimal.valueOf(2.25), LocalDate.of(2018,
                8,20), 4000L, BigDecimal.valueOf(2.30), LocalDate.of(2018, 8, 28)));
        statisticsDtos.add(new StatisticsDto(1L, "KREZUS", BigDecimal.valueOf(2.02), LocalDate.of(2018,
                8,15), 5000L, BigDecimal.valueOf(2.67), LocalDate.of(2018, 8, 28)));
        List<Statistics> statistics = new ArrayList<>();
        statistics.add(new Statistics(new User(1L), "COGNOR", BigDecimal.valueOf(2.22), LocalDate.of(2018,
                8,20), 3000L, BigDecimal.valueOf(2.30), LocalDate.of(2018, 8, 28)));
        statistics.add(new Statistics(new User(1L), "COGNOR", BigDecimal.valueOf(2.25), LocalDate.of(2018,
                8,20), 4000L, BigDecimal.valueOf(2.30), LocalDate.of(2018, 8, 28)));
        statistics.add(new Statistics(new User(1L), "KREZUS", BigDecimal.valueOf(2.02), LocalDate.of(2018,
                8,15), 5000L, BigDecimal.valueOf(2.67), LocalDate.of(2018, 8, 28)));
        when(statisticsMapper.mapperToListDomain(statisticsDtos)).thenReturn(statistics);
        when(statisticsService.addNewStatistics(statistics.get(0))).thenReturn(statistics.get(0));
        when(statisticsService.addNewStatistics(statistics.get(1))).thenReturn(statistics.get(1));
        when(statisticsService.addNewStatistics(statistics.get(2))).thenReturn(statistics.get(2));
        //when
        statisticsCreation.saveAllStatistics(statisticsDtos);
        //then
        verify(statisticsMapper, times(1)).mapperToListDomain(statisticsDtos);
        verify(statisticsService, times(1)).addNewStatistics(statistics.get(0));
        verify(statisticsService, times(1)).addNewStatistics(statistics.get(1));
        verify(statisticsService, times(1)).addNewStatistics(statistics.get(2));
    }

}

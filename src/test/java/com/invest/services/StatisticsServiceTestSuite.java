package com.invest.services;

import com.invest.domain.Statistics;
import com.invest.domain.User;
import com.invest.repositories.StatisticsDao;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StatisticsServiceTestSuite {

    @InjectMocks
    private StatisticsService statisticsService;

    @Mock
    private StatisticsDao statisticsDao;

    @Test
    public void testAddNewStatistics() {
        //given
        Statistics statistics = new Statistics(1L, new User(11L), "PKNORLEN", BigDecimal.valueOf(22.01), LocalDate.of(2018, 5, 4),
                BigDecimal.valueOf(23.91), LocalDate.of(2018, 7, 11), 1300L);

        when(statisticsDao.save(statistics)).thenReturn(statistics);
        //when
        Statistics savedStatistics = statisticsService.addNewStatistics(statistics);
        //then
        Assert.assertEquals(statistics, savedStatistics);
    }

    @Test
    public void testShowAllOfUser() {
        //given
        List<Statistics> statistics = new ArrayList<>();
        Statistics statistics1 = new Statistics(1L, new User(11L), "PKNORLEN", BigDecimal.valueOf(22.01), LocalDate.of(2018, 5, 4),
                BigDecimal.valueOf(23.91), LocalDate.of(2018, 7, 11), 1300L);
        Statistics statistics2 = new Statistics(11L, new User(12L), "PKNORLEN", BigDecimal.valueOf(22.01), LocalDate.of(2018, 5, 4),
                BigDecimal.valueOf(23.91), LocalDate.of(2018, 7, 11), 1300L);
        statistics.add(statistics1);
        statistics.add(statistics2);
        when(statisticsDao.findAll()).thenReturn(statistics);
        //when
        List<Statistics> gotStatistics = statisticsService.showAllOfUser(11L);
        //then
        Assert.assertNotEquals(statistics.size(), gotStatistics.size());
        Assert.assertEquals(1, gotStatistics.size());
        Assert.assertEquals(1L, gotStatistics.get(0).getId().longValue());
    }

}

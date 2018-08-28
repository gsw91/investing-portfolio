package com.invest.controller;

import com.invest.domain.Statistics;
import com.invest.domain.User;
import com.invest.dtos.StatisticsDto;
import com.invest.mappers.StatisticsMapper;
import com.invest.services.StatisticsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(StatisticsController.class)
public class StatisticsControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StatisticsService service;

    @MockBean
    private StatisticsMapper mapper;

    @Test
    public void shouldShowUserStatistics() throws Exception{
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

        when(service.showAllOfUser(1L)).thenReturn(statistics);
        when(mapper.mapperToListDto(statistics)).thenReturn(statisticsDtos);
        //when & then
        mockMvc.perform(get("/v1/stats/show").contentType(MediaType.APPLICATION_JSON).param("userId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].instrumentName", is("COGNOR")))
                .andExpect(jsonPath("$[1].instrumentName", is("COGNOR")))
                .andExpect(jsonPath("$[2].instrumentName", is("KREZUS")));

    }

}

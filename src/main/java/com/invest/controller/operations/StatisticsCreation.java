package com.invest.controller.operations;

import com.invest.domain.Statistics;
import com.invest.dtos.StatisticsDto;
import com.invest.mappers.StatisticsMapper;
import com.invest.services.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatisticsCreation {

    @Autowired
    private StatisticsService statisticsService;

    @Autowired
    private StatisticsMapper statisticsMapper;

    public boolean saveAllStatistics(List<StatisticsDto> statisticsDtoList) {
        if(statisticsDtoList.size()>0) {
            List<Statistics> statistics = statisticsMapper.mapperToListDomain(statisticsDtoList);
            for (Statistics stats : statistics) {
                statisticsService.addNewStatistics(stats);
            }
            return true;
        } else {
            return false;
        }
    }



}

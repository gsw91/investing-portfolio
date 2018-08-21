package com.invest.services;

import com.invest.domain.Statistics;
import com.invest.repositories.StatisticsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StatisticsService {

    @Autowired
    private StatisticsDao statisticsDao;

    public Statistics addNewStatistics(Statistics statistics) {
        return statisticsDao.save(statistics);
    }

    public List<Statistics> showAll() {
        return statisticsDao.findAll();
    }

}

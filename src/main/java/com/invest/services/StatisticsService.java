package com.invest.services;

import com.invest.domain.Statistics;
import com.invest.repositories.StatisticsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StatisticsService {

    @Autowired
    private StatisticsDao statisticsDao;

    public Statistics addNewStatistics(Statistics statistics) {
        return statisticsDao.save(statistics);
    }

    public List<Statistics> showAllOfUser(Long userId) {
        return statisticsDao.findAll().stream()
                .filter(t -> t.getUser().getId().longValue() == userId)
                .collect(Collectors.toList());
    }

    public long countStatistics() {
        return statisticsDao.count();
    }

}

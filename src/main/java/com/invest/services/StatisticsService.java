package com.invest.services;

import com.invest.domain.Statistics;
import com.invest.repositories.StatisticsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        if (statisticsDao.count()!=0) {
            return statisticsDao.findAll().stream()
                    .filter(t -> t.getUser().getId().longValue() == userId)
                    .collect(Collectors.toList());
        } else {
            return new ArrayList<>();
        }
    }

    public long countStatistics() {
        return statisticsDao.count();
    }

    public void deleteAllUsersStatistics(Long userId) {
        showAllOfUser(userId).forEach(statisticsDao::delete);
    }

}

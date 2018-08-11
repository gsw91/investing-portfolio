package com.invest.repositories;

import com.invest.tables.Statistics;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface StatisticsDao extends CrudRepository<Statistics, Long> {

}

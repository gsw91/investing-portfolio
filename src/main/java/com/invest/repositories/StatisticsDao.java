package com.invest.repositories;

import com.invest.domain.Statistics;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
@Transactional
public interface StatisticsDao extends CrudRepository<Statistics, Long> {

    @Override
    <S extends Statistics> S save(S entity);

    @Override
    List<Statistics> findAll();

    @Override
    default void deleteById(Long aLong) {

    }

}

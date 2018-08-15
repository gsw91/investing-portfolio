package com.invest.repositories;

import com.invest.domain.Statistics;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface StatisticsDao extends CrudRepository<Statistics, Long> {

    @Override
    <S extends Statistics> S save(S entity);

    @Override
    Optional<Statistics> findById(Long aLong);

    @Override
    boolean existsById(Long aLong);

    @Override
    long count();

    @Override
    void deleteById(Long aLong);

}

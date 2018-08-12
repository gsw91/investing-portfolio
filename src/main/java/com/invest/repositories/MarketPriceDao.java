package com.invest.repositories;

import com.invest.tables.MarketPrice;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface MarketPriceDao extends CrudRepository<MarketPrice, Long> {

    @Override
    <S extends MarketPrice> S save(S entity);

    @Override
    Optional<MarketPrice> findById(Long aLong);

    @Override
    boolean existsById(Long aLong);

    @Override
    long count();

    @Override
    void deleteById(Long aLong);

}

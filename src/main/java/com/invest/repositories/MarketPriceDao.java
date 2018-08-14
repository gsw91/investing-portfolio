package com.invest.repositories;

import com.invest.tables.MarketPrice;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface MarketPriceDao extends CrudRepository<MarketPrice, Long> {

    @Override
    <S extends MarketPrice> S save(S entity);

    @Override
    Optional<MarketPrice> findById(Long aLong);

    @Override
    List<MarketPrice> findAll();

    @Override
    long count();

}

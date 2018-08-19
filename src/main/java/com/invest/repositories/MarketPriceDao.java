package com.invest.repositories;

import com.invest.domain.MarketPrice;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
@Transactional
public interface MarketPriceDao extends CrudRepository<MarketPrice, Long> {

    @Override
    <S extends MarketPrice> List<S> saveAll(Iterable<S> entities);

    @Override
    List<MarketPrice> findAll();

}

package com.invest.repositories;

import com.invest.domain.Instrument;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface InstrumentDao extends CrudRepository<Instrument, Long> {

    @Override
    <S extends Instrument> S save(S entity);

    @Override
    Optional<Instrument> findById(Long aLong);

    @Override
    boolean existsById(Long aLong);

    @Override
    long count();

    @Override
    void deleteById(Long aLong);

}

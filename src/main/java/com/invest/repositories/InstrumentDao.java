package com.invest.repositories;

import com.invest.domain.Instrument;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface InstrumentDao extends CrudRepository<Instrument, Long> {

    @Override
    <S extends Instrument> S save(S entity);

    @Override
    List<Instrument> findAll();

    @Override
    void deleteById(Long aLong);

    @Override
    default void delete(Instrument instrument) {

    }
}

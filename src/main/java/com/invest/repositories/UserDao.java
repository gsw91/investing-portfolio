package com.invest.repositories;

import com.invest.tables.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface UserDao extends CrudRepository<User, Long> {

    @Override
    <S extends User> S save(S entity);

    @Override
    Iterable<User> findAll();

    @Override
    Optional<User> findById(Long aLong);

    @Override
    boolean existsById(Long aLong);

    @Override
    long count();

    @Override
    void deleteById(Long aLong);

}

package com.invest.repositories;

import com.invest.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
@Transactional
public interface UserDao extends CrudRepository<User, Long> {

    @Override
    <S extends User> S save(S entity);

    @Override
    List<User> findAll();

    @Override
    void deleteById(Long aLong);

}

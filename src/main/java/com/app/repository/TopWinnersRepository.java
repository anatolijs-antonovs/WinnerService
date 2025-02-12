package com.app.repository;

import com.app.entity.TopWinner;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopWinnersRepository extends CrudRepository<TopWinner, Integer> {
}

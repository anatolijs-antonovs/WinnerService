package com.app.repository;

import com.app.entity.Winner;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WinnerRepository extends CrudRepository<Winner, Integer> {
}

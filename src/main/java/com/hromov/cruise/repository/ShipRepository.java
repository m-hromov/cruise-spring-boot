package com.hromov.cruise.repository;

import com.hromov.cruise.domain.Ship;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipRepository extends CrudRepository<Ship, Long> {

}

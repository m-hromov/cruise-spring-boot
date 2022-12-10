package com.hromov.cruise.repository;

import com.hromov.cruise.domain.Station;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StationRepository extends CrudRepository<Station, Long> {

}

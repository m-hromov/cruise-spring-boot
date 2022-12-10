package com.hromov.cruise.repository;

import com.hromov.cruise.domain.Cruise;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CruiseRepository extends CrudRepository<Cruise, Long> {

}

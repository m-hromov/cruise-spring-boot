package com.hromov.cruise.repository;

import com.hromov.cruise.model.Cruise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CruiseRepository extends JpaRepository<Cruise, Long> {

}

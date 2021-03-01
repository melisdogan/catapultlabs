package com.melisdogan.catapult_labs.repository;

import com.melisdogan.catapult_labs.model.Meter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MeterRepository extends JpaRepository<Meter, Long> {
}

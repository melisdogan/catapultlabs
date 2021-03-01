package com.melisdogan.catapult_labs.repository;


import com.melisdogan.catapult_labs.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}

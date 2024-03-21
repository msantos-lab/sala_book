package com.salabook.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salabook.server.entities.Sala;

public interface SalaRepository extends JpaRepository<Sala, Long> {

}
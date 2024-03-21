package com.salabook.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salabook.server.entities.Reserva;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {

}
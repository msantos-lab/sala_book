package com.salabook.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salabook.server.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
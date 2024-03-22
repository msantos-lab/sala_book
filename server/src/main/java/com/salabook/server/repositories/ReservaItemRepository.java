package com.salabook.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salabook.server.entities.ReservaItem;
import com.salabook.server.entities.pk.ReservaItemPK;

public interface ReservaItemRepository extends JpaRepository<ReservaItem, ReservaItemPK> {
   
}
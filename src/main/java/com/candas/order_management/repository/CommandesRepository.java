package com.candas.order_management.repository;

import com.candas.order_management.models.Commandes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommandesRepository extends JpaRepository<Commandes, Long> {
}

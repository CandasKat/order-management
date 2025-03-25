package com.candas.order_management;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommandesProduitsRepository extends JpaRepository<CommandesProduits, Long> {
}

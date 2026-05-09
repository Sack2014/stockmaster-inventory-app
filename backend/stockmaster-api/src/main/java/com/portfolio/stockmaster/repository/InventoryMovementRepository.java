package com.portfolio.stockmaster.repository;

import com.portfolio.stockmaster.model.InventoryMovement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventoryMovementRepository extends JpaRepository<InventoryMovement, Long> {
    List<InventoryMovement> findTop10ByOrderByDateDesc();
    List<InventoryMovement> findByProductIdOrderByDateDesc(Long productId);
}

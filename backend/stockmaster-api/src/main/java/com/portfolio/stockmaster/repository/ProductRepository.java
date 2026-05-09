package com.portfolio.stockmaster.repository;

import com.portfolio.stockmaster.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByCodeIgnoreCase(String code);
    boolean existsByCodeIgnoreCase(String code);

    @Query("""
            SELECT p FROM Product p
            WHERE (:search IS NULL OR LOWER(p.name) LIKE LOWER(CONCAT('%', :search, '%'))
                OR LOWER(p.code) LIKE LOWER(CONCAT('%', :search, '%')))
            AND (:categoryId IS NULL OR p.category.id = :categoryId)
            ORDER BY p.name ASC
            """)
    List<Product> search(@Param("search") String search, @Param("categoryId") Long categoryId);

    @Query("SELECT p FROM Product p WHERE p.currentStock <= p.minimumStock ORDER BY p.currentStock ASC")
    List<Product> findLowStockProducts();
}

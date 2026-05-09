package com.portfolio.stockmaster.service;

import com.portfolio.stockmaster.dto.DashboardResponse;
import com.portfolio.stockmaster.model.Product;
import com.portfolio.stockmaster.repository.CategoryRepository;
import com.portfolio.stockmaster.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class DashboardService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public DashboardService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public DashboardResponse getSummary() {
        long totalProducts = productRepository.count();
        long totalCategories = categoryRepository.count();
        long lowStockProducts = productRepository.findLowStockProducts().size();
        BigDecimal inventoryValue = productRepository.findAll().stream()
                .map(this::calculateProductValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return new DashboardResponse(totalProducts, totalCategories, lowStockProducts, inventoryValue);
    }

    private BigDecimal calculateProductValue(Product product) {
        return product.getPrice().multiply(BigDecimal.valueOf(product.getCurrentStock()));
    }
}

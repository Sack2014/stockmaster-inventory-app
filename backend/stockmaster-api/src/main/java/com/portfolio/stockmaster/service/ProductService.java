package com.portfolio.stockmaster.service;

import com.portfolio.stockmaster.dto.ProductRequest;
import com.portfolio.stockmaster.exception.BusinessException;
import com.portfolio.stockmaster.exception.ResourceNotFoundException;
import com.portfolio.stockmaster.model.Category;
import com.portfolio.stockmaster.model.Product;
import com.portfolio.stockmaster.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    public ProductService(ProductRepository productRepository, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
    }

    public List<Product> search(String search, Long categoryId) {
        String normalizedSearch = (search == null || search.isBlank()) ? null : search.trim();
        return productRepository.search(normalizedSearch, categoryId);
    }

    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
    }

    public Product create(ProductRequest request) {
        if (productRepository.existsByCodeIgnoreCase(request.getCode())) {
            throw new BusinessException("A product with this code already exists");
        }
        Product product = new Product();
        applyRequest(product, request);
        return productRepository.save(product);
    }

    public Product update(Long id, ProductRequest request) {
        Product product = findById(id);
        productRepository.findByCodeIgnoreCase(request.getCode()).ifPresent(existing -> {
            if (!existing.getId().equals(id)) {
                throw new BusinessException("A product with this code already exists");
            }
        });
        applyRequest(product, request);
        return productRepository.save(product);
    }

    public void delete(Long id) {
        Product product = findById(id);
        productRepository.delete(product);
    }

    public List<Product> findLowStockProducts() {
        return productRepository.findLowStockProducts();
    }

    private void applyRequest(Product product, ProductRequest request) {
        Category category = categoryService.findById(request.getCategoryId());
        product.setName(request.getName());
        product.setCode(request.getCode());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setCurrentStock(request.getCurrentStock());
        product.setMinimumStock(request.getMinimumStock());
        product.setCategory(category);
    }
}

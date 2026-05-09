package com.portfolio.stockmaster.service;

import com.portfolio.stockmaster.exception.BusinessException;
import com.portfolio.stockmaster.exception.ResourceNotFoundException;
import com.portfolio.stockmaster.model.Category;
import com.portfolio.stockmaster.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category findById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));
    }

    public Category create(Category category) {
        if (categoryRepository.existsByNameIgnoreCase(category.getName())) {
            throw new BusinessException("A category with this name already exists");
        }
        return categoryRepository.save(category);
    }

    public Category update(Long id, Category request) {
        Category category = findById(id);
        categoryRepository.findByNameIgnoreCase(request.getName()).ifPresent(existing -> {
            if (!existing.getId().equals(id)) {
                throw new BusinessException("A category with this name already exists");
            }
        });
        category.setName(request.getName());
        category.setDescription(request.getDescription());
        return categoryRepository.save(category);
    }

    public void delete(Long id) {
        Category category = findById(id);
        categoryRepository.delete(category);
    }
}

package com.portfolio.stockmaster.config;

import com.portfolio.stockmaster.model.Category;
import com.portfolio.stockmaster.model.Product;
import com.portfolio.stockmaster.repository.CategoryRepository;
import com.portfolio.stockmaster.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initData(CategoryRepository categoryRepository, ProductRepository productRepository) {
        return args -> {
            if (categoryRepository.count() == 0) {
                Category electronics = categoryRepository.save(new Category("Electronics", "Electronic products and accessories"));
                Category office = categoryRepository.save(new Category("Office", "Office supplies"));
                Category groceries = categoryRepository.save(new Category("Groceries", "Basic grocery products"));

                Product keyboard = new Product();
                keyboard.setName("Mechanical Keyboard");
                keyboard.setCode("ELEC-001");
                keyboard.setDescription("USB mechanical keyboard");
                keyboard.setPrice(new BigDecimal("185000"));
                keyboard.setCurrentStock(8);
                keyboard.setMinimumStock(5);
                keyboard.setCategory(electronics);
                productRepository.save(keyboard);

                Product notebook = new Product();
                notebook.setName("Premium Notebook");
                notebook.setCode("OFF-001");
                notebook.setDescription("Hardcover notebook");
                notebook.setPrice(new BigDecimal("18000"));
                notebook.setCurrentStock(3);
                notebook.setMinimumStock(10);
                notebook.setCategory(office);
                productRepository.save(notebook);

                Product coffee = new Product();
                coffee.setName("Coffee Pack");
                coffee.setCode("GRO-001");
                coffee.setDescription("Colombian coffee 500g");
                coffee.setPrice(new BigDecimal("22000"));
                coffee.setCurrentStock(20);
                coffee.setMinimumStock(6);
                coffee.setCategory(groceries);
                productRepository.save(coffee);
            }
        };
    }
}

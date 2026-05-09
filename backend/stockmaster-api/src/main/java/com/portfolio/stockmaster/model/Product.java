package com.portfolio.stockmaster.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Product name is required")
    @Size(max = 120, message = "Product name must not exceed 120 characters")
    @Column(nullable = false, length = 120)
    private String name;

    @NotBlank(message = "Product code is required")
    @Size(max = 50, message = "Code must not exceed 50 characters")
    @Column(nullable = false, unique = true, length = 50)
    private String code;

    @Size(max = 255, message = "Description must not exceed 255 characters")
    private String description;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", inclusive = true, message = "Price must be zero or greater")
    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal price;

    @Min(value = 0, message = "Current stock must be zero or greater")
    @Column(nullable = false)
    private Integer currentStock = 0;

    @Min(value = 0, message = "Minimum stock must be zero or greater")
    @Column(nullable = false)
    private Integer minimumStock = 0;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    public Product() {
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
    public Integer getCurrentStock() { return currentStock; }
    public void setCurrentStock(Integer currentStock) { this.currentStock = currentStock; }
    public Integer getMinimumStock() { return minimumStock; }
    public void setMinimumStock(Integer minimumStock) { this.minimumStock = minimumStock; }
    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }

    public boolean isLowStock() {
        return currentStock <= minimumStock;
    }
}

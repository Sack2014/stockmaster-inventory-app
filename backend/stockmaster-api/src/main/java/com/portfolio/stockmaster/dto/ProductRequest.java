package com.portfolio.stockmaster.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public class ProductRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String code;
    private String description;
    @NotNull
    @DecimalMin("0.0")
    private BigDecimal price;
    @NotNull
    @Min(0)
    private Integer currentStock;
    @NotNull
    @Min(0)
    private Integer minimumStock;
    @NotNull
    private Long categoryId;

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
    public Long getCategoryId() { return categoryId; }
    public void setCategoryId(Long categoryId) { this.categoryId = categoryId; }
}

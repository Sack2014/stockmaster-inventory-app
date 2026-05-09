package com.portfolio.stockmaster.dto;

import java.math.BigDecimal;

public class DashboardResponse {
    private long totalProducts;
    private long totalCategories;
    private long lowStockProducts;
    private BigDecimal inventoryValue;

    public DashboardResponse(long totalProducts, long totalCategories, long lowStockProducts, BigDecimal inventoryValue) {
        this.totalProducts = totalProducts;
        this.totalCategories = totalCategories;
        this.lowStockProducts = lowStockProducts;
        this.inventoryValue = inventoryValue;
    }

    public long getTotalProducts() { return totalProducts; }
    public long getTotalCategories() { return totalCategories; }
    public long getLowStockProducts() { return lowStockProducts; }
    public BigDecimal getInventoryValue() { return inventoryValue; }
}

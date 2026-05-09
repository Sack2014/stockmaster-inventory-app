package com.portfolio.stockmaster.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class MovementRequest {
    @NotNull
    private Long productId;
    @NotNull
    @Min(1)
    private Integer quantity;
    private String observation;

    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    public String getObservation() { return observation; }
    public void setObservation(String observation) { this.observation = observation; }
}

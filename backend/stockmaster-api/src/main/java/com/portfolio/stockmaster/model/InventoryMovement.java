package com.portfolio.stockmaster.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

@Entity
@Table(name = "inventory_movements")
public class InventoryMovement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Movement type is required")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private MovementType type;

    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "Quantity must be greater than zero")
    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private LocalDateTime date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Size(max = 255, message = "Observation must not exceed 255 characters")
    private String observation;

    @PrePersist
    public void prePersist() {
        if (date == null) {
            date = LocalDateTime.now();
        }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public MovementType getType() { return type; }
    public void setType(MovementType type) { this.type = type; }
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    public LocalDateTime getDate() { return date; }
    public void setDate(LocalDateTime date) { this.date = date; }
    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }
    public String getObservation() { return observation; }
    public void setObservation(String observation) { this.observation = observation; }
}

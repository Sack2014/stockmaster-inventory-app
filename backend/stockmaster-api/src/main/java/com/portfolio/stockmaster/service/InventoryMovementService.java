package com.portfolio.stockmaster.service;

import com.portfolio.stockmaster.dto.MovementRequest;
import com.portfolio.stockmaster.exception.BusinessException;
import com.portfolio.stockmaster.model.InventoryMovement;
import com.portfolio.stockmaster.model.MovementType;
import com.portfolio.stockmaster.model.Product;
import com.portfolio.stockmaster.repository.InventoryMovementRepository;
import com.portfolio.stockmaster.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InventoryMovementService {
    private final InventoryMovementRepository movementRepository;
    private final ProductService productService;
    private final ProductRepository productRepository;

    public InventoryMovementService(InventoryMovementRepository movementRepository, ProductService productService, ProductRepository productRepository) {
        this.movementRepository = movementRepository;
        this.productService = productService;
        this.productRepository = productRepository;
    }

    public List<InventoryMovement> findAll() {
        return movementRepository.findAll();
    }

    public List<InventoryMovement> findLastMovements() {
        return movementRepository.findTop10ByOrderByDateDesc();
    }

    @Transactional
    public InventoryMovement registerEntry(MovementRequest request) {
        Product product = productService.findById(request.getProductId());
        product.setCurrentStock(product.getCurrentStock() + request.getQuantity());
        productRepository.save(product);
        return saveMovement(product, MovementType.ENTRY, request);
    }

    @Transactional
    public InventoryMovement registerExit(MovementRequest request) {
        Product product = productService.findById(request.getProductId());
        if (product.getCurrentStock() < request.getQuantity()) {
            throw new BusinessException("Not enough stock available for this exit movement");
        }
        product.setCurrentStock(product.getCurrentStock() - request.getQuantity());
        productRepository.save(product);
        return saveMovement(product, MovementType.EXIT, request);
    }

    private InventoryMovement saveMovement(Product product, MovementType type, MovementRequest request) {
        InventoryMovement movement = new InventoryMovement();
        movement.setProduct(product);
        movement.setType(type);
        movement.setQuantity(request.getQuantity());
        movement.setObservation(request.getObservation());
        return movementRepository.save(movement);
    }
}

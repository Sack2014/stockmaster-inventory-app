package com.portfolio.stockmaster.controller;

import com.portfolio.stockmaster.dto.MovementRequest;
import com.portfolio.stockmaster.model.InventoryMovement;
import com.portfolio.stockmaster.service.InventoryMovementService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryMovementController {
    private final InventoryMovementService movementService;

    public InventoryMovementController(InventoryMovementService movementService) {
        this.movementService = movementService;
    }

    @GetMapping("/movements")
    public List<InventoryMovement> findAll() {
        return movementService.findAll();
    }

    @GetMapping("/movements/latest")
    public List<InventoryMovement> latest() {
        return movementService.findLastMovements();
    }

    @PostMapping("/entry")
    @ResponseStatus(HttpStatus.CREATED)
    public InventoryMovement entry(@Valid @RequestBody MovementRequest request) {
        return movementService.registerEntry(request);
    }

    @PostMapping("/exit")
    @ResponseStatus(HttpStatus.CREATED)
    public InventoryMovement exit(@Valid @RequestBody MovementRequest request) {
        return movementService.registerExit(request);
    }
}

package com.fiap.techchallenge.fourlanches.adapter.driver.api.controllers;

import com.fiap.techchallenge.fourlanches.application.dto.OrderDTO;
import com.fiap.techchallenge.fourlanches.domain.entities.Order;
import com.fiap.techchallenge.fourlanches.domain.exception.InvalidOrderException;
import com.fiap.techchallenge.fourlanches.domain.usecases.OrderUseCase;
import com.fiap.techchallenge.fourlanches.domain.valueobjects.OrderStatus;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("orders")
public class OrderController {

    private OrderUseCase orderUseCase;

    @GetMapping(value = "", produces = "application/json")
    @ApiResponse(responseCode = "200")
    public List<Order> getOrders() {
        return orderUseCase.getAllPendingOrdersOrderedByStatusAndCreatedAt();
    }

    @GetMapping(value = "/status/{status}", produces = "application/json")
    @ApiResponse(responseCode = "200")
    public List<Order> getOrdersByStatus(@PathVariable OrderStatus status) {
        return orderUseCase.getOrdersByStatus(status);
    }

    @PostMapping(value = "", produces = "application/json")
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createOrder(@RequestBody OrderDTO orderDTO) throws InvalidOrderException {
        Long returnedId = orderUseCase.createOrder(orderDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(returnedId);
    }

}

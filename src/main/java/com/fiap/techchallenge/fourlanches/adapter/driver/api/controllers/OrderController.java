package com.fiap.techchallenge.fourlanches.adapter.driver.api.controllers;

import com.fiap.techchallenge.fourlanches.application.usecases.OrderUseCase;
import com.fiap.techchallenge.fourlanches.domain.entities.Order;
import com.fiap.techchallenge.fourlanches.domain.exception.InvalidOrderException;
import com.fiap.techchallenge.fourlanches.domain.valueobjects.OrderStatus;
import com.fiap.techchallenge.fourlanches.application.dto.OrderDTO;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("orders")
public class OrderController {

    private OrderUseCase orderUseCase;

    @PostMapping(value = "/", produces = "application/json")
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createOrder(@RequestBody OrderDTO orderDTO) throws InvalidOrderException {
        Long returnedId = orderUseCase.createOrder(orderDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(returnedId);
    }

    @GetMapping(value = "/status/{status}", produces = "application/json")
    @ApiResponse(responseCode = "200")
    public List<Order> getOrdersByStatus(@PathVariable OrderStatus status) {
        return orderUseCase.getOrdersByStatus(status);
    }

}

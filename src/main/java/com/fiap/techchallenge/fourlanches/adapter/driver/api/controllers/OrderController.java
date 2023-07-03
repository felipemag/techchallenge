package com.fiap.techchallenge.fourlanches.adapter.driver.api.controllers;

import com.fiap.techchallenge.fourlanches.domain.aggregates.OrderAggregate;
import com.fiap.techchallenge.fourlanches.domain.exception.InvalidOrderException;
import com.fiap.techchallenge.fourlanches.domain.valueobjects.OrderVO;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("orders")
public class OrderController {

    private OrderAggregate orderAggregate;

    @PostMapping(value = "/", produces = "application/json")
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createOrder(@RequestBody OrderVO orderVO) throws InvalidOrderException {
        Long returnedId = orderAggregate.createOrder(orderVO);
        return ResponseEntity.status(HttpStatus.CREATED).body(returnedId);
    }

}

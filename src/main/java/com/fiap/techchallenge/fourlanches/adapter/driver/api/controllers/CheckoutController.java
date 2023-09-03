package com.fiap.techchallenge.fourlanches.adapter.driver.api.controllers;

import com.fiap.techchallenge.fourlanches.application.dto.OrderDTO;
import com.fiap.techchallenge.fourlanches.domain.exception.InvalidOrderException;
import com.fiap.techchallenge.fourlanches.domain.usecases.CheckoutUseCase;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("checkout")
public class CheckoutController {

    private CheckoutUseCase checkoutUseCase;

    @PostMapping
    @ApiResponse(responseCode = "200")
    public Long processPayment(@RequestBody OrderDTO orderDTO) throws InvalidOrderException {
        return checkoutUseCase.processPayment(orderDTO);
    }
}

package com.fiap.techchallenge.fourlanches.adapter.driver.api.controllers;

import com.fiap.techchallenge.fourlanches.application.usecases.CheckoutUseCase;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@AllArgsConstructor
@RequestMapping("checkout")
public class CheckoutController {

    private CheckoutUseCase checkoutUseCase;

    @PostMapping
    @ApiResponse(responseCode = "200")
    public boolean processPayment(@RequestParam long orderId, @RequestParam BigDecimal totalAmount) {
        return checkoutUseCase.processPayment(orderId, totalAmount);
    }

}

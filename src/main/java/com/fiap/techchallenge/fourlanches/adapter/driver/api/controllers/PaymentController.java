package com.fiap.techchallenge.fourlanches.adapter.driver.api.controllers;

import com.fiap.techchallenge.fourlanches.domain.usecases.OrderUseCase;
import com.fiap.techchallenge.fourlanches.domain.valueobjects.OrderStatus;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("payment")
public class PaymentController {
    OrderUseCase orderUseCase;
    @PostMapping("/webhook")
    @ApiResponse(responseCode = "200")
    public void receivePaymentState(@RequestBody PaymentState paymentState){
        switch (paymentState.type){
            case "approved" -> orderUseCase.updateOrderStatus(paymentState.orderId, OrderStatus.RECEIVED);
            case "canceled", "error" -> orderUseCase.updateOrderStatus(paymentState.orderId, OrderStatus.CANCELED);
        }
    }
}

package com.fiap.techchallenge.fourlanches.adapter.driver.api.controllers;

import com.fiap.techchallenge.fourlanches.domain.usecases.PaymentUseCase;
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

    @PostMapping("/webhook")
    @ApiResponse(responseCode = "200")
    public void receivePaymentState(@RequestBody PaymentState paymentState){
        if("approved".equals(paymentState.type)) {
            paymentUseCase.approvePayment(paymentState.orderId);
        }

    }
    PaymentUseCase paymentUseCase;
}

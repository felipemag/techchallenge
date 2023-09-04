package com.fiap.techchallenge.fourlanches.adapter.driver.api.controllers;

import com.fiap.techchallenge.fourlanches.domain.usecases.PaymentUseCase;
import com.fiap.techchallenge.fourlanches.domain.valueobjects.PaymentStatus;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("payment")
public class PaymentController {

    PaymentUseCase paymentUseCase;

    @PostMapping("/webhook")
    @ApiResponse(responseCode = "200")
    public void receivePaymentState(@RequestBody PaymentStateRequest paymentStateRequest){
        if("approved".equals(paymentStateRequest.type)) {
            paymentUseCase.approvePayment(paymentStateRequest.orderId);
        }

    }

    @GetMapping(value = "/order/{orderId}", produces = "application/json")
    @ApiResponse(responseCode = "200")
    public ResponseEntity<PaymentStatusResponse> getPaymentStatusByOrderId(@PathVariable Long orderId){
        PaymentStatus paymentStatus = paymentUseCase.getPaymentStatusByOrderId(orderId);
        return ResponseEntity.ok().body(PaymentStatusResponse.builder()
                        .orderId(orderId)
                        .status(paymentStatus)
                .build());
    }
}

package com.fiap.techchallenge.fourlanches.adapter.driver.api.controllers;

import com.fiap.techchallenge.fourlanches.domain.entities.PaymentState;
import com.fiap.techchallenge.fourlanches.domain.usecases.PaymentUseCase;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("payment")
public class PaymentController {

    PaymentUseCase paymentUseCase;
    @PostMapping("/webhook")
    @ApiResponse(responseCode = "200")
    public void receivePaymentState(@RequestBody com.fiap.techchallenge.fourlanches.adapter.driver.api.controllers.PaymentState paymentState){
        if("approved".equals(paymentState.type)) {
            paymentUseCase.approvePayment(paymentState.orderId);
        }

    }
    @GetMapping(value = "/order/{id}/", produces = "application/json")
    @ApiResponse(responseCode = "200")
    public ResponseEntity<PaymentState> getPaymentStateByOrderId(@PathVariable Long id){
        PaymentState paymentState = paymentUseCase.getPaymentStateByOrderId(id);
        return ResponseEntity.ok().body(paymentState);
    }
}

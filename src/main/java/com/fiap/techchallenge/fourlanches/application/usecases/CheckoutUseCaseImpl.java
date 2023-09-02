package com.fiap.techchallenge.fourlanches.application.usecases;

import com.fiap.techchallenge.fourlanches.application.PaymentGateway;
import com.fiap.techchallenge.fourlanches.domain.usecases.CheckoutUseCase;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Slf4j
@Service
@AllArgsConstructor
public class CheckoutUseCaseImpl implements CheckoutUseCase {

    private final PaymentGateway gateway;

    public boolean processPayment(long orderId, BigDecimal totalAmount){
        return gateway.processPayment(orderId, totalAmount);
    }
}

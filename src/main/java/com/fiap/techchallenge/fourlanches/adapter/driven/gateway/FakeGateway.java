package com.fiap.techchallenge.fourlanches.adapter.driven.gateway;

import com.fiap.techchallenge.fourlanches.application.PaymentGateway;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class FakeGateway implements PaymentGateway {

    @Override
    public boolean processPayment(long orderId, BigDecimal totalAmount){
        return true;
    }

}

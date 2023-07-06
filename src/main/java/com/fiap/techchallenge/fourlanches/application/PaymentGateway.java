package com.fiap.techchallenge.fourlanches.application;

import java.math.BigDecimal;

public interface PaymentGateway {

    boolean processPayment(long orderId, BigDecimal totalAmount);

}

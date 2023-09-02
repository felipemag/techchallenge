package com.fiap.techchallenge.fourlanches.domain.usecases;

import java.math.BigDecimal;

public interface CheckoutUseCase {
    boolean processPayment(long orderId, BigDecimal totalAmount);
}

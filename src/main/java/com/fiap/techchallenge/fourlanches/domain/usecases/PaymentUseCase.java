package com.fiap.techchallenge.fourlanches.domain.usecases;

import com.fiap.techchallenge.fourlanches.domain.entities.PaymentState;

public interface PaymentUseCase {
    void approvePayment(Long id);
    PaymentState getPaymentStateByOrderId(Long id);
}

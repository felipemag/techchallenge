package com.fiap.techchallenge.fourlanches.domain.usecases;

import com.fiap.techchallenge.fourlanches.domain.valueobjects.PaymentStatus;

public interface PaymentUseCase {
    void approvePayment(Long id);
    PaymentStatus getPaymentStatusByOrderId(Long id);
}

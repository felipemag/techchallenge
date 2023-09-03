package com.fiap.techchallenge.fourlanches.domain.usecases;

import com.fiap.techchallenge.fourlanches.application.dto.OrderDTO;

public interface CheckoutUseCase {
    Long processPayment(OrderDTO orderDTO);
}

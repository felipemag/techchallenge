package com.fiap.techchallenge.fourlanches.domain.usecases;

import com.fiap.techchallenge.fourlanches.domain.entities.Order;

public interface ValidateOrderStatusUseCase {
    void validateOrderCreated(Order order);
    void validateOrderReceived(Order order);
    void validateOrderInPreparation(Order order);
    void validateOrderReady(Order order);
    void validateOrderFinished(Order order);
    void validateOrderCanceled(Order order);
}

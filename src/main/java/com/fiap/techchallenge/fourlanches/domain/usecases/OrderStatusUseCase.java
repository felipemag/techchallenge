package com.fiap.techchallenge.fourlanches.domain.usecases;

import com.fiap.techchallenge.fourlanches.domain.entities.Order;

public interface OrderStatusUseCase {
    void orderCreated(Order order);
    void orderReceived(Order order);
    void orderInPreparation(Order order);
    void orderReady(Order order);
    void orderFinished(Order order);
    void orderCanceled(Order order);
}

package com.fiap.techchallenge.fourlanches.domain.usecases;

import com.fiap.techchallenge.fourlanches.application.dto.OrderDTO;
import com.fiap.techchallenge.fourlanches.domain.entities.Order;
import com.fiap.techchallenge.fourlanches.domain.valueobjects.OrderStatus;

import java.util.List;

public interface OrderUseCase {
    List<Order> getAllPendingOrdersOrderedByStatusAndCreatedAt();
    List<Order> getOrdersByStatus(OrderStatus status);
    Order createOrder(OrderDTO orderDTO);
    void receiveOrder(Long orderId, boolean paymentApproved);
    void orderInPreparation(Long orderId);
    void orderReady(Long orderId);
    void orderFinished(Long orderId);
    void orderCanceled(Long orderId);
    Order getById(Long orderId);
}

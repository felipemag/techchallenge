package com.fiap.techchallenge.fourlanches.domain.usecases;

import com.fiap.techchallenge.fourlanches.application.dto.OrderDTO;
import com.fiap.techchallenge.fourlanches.domain.entities.Order;
import com.fiap.techchallenge.fourlanches.domain.valueobjects.OrderStatus;

import java.util.List;

public interface OrderUseCase {
    List<Order> getAllPendingOrdersOrderedByStatusAndCreatedAt();
    Long createOrder(OrderDTO orderDTO);
    List<Order> getOrdersByStatus(OrderStatus status);
    void updateOrder(Long id, OrderDTO orderDTO);
    Order getById(Long id);
}

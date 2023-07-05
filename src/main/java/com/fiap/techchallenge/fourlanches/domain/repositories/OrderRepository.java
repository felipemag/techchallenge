package com.fiap.techchallenge.fourlanches.domain.repositories;

import com.fiap.techchallenge.fourlanches.domain.entities.Order;
import com.fiap.techchallenge.fourlanches.domain.valueobjects.OrderStatus;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository {

    Long create(Order order);

    List<Order> getOrdersByStatus(OrderStatus status);

}

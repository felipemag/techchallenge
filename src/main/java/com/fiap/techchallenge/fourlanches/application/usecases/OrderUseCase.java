package com.fiap.techchallenge.fourlanches.application.usecases;

import com.fiap.techchallenge.fourlanches.domain.entities.Order;
import com.fiap.techchallenge.fourlanches.domain.exception.InvalidOrderException;
import com.fiap.techchallenge.fourlanches.domain.repositories.OrderRepository;
import com.fiap.techchallenge.fourlanches.domain.valueobjects.OrderStatus;
import com.fiap.techchallenge.fourlanches.application.dto.OrderDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderUseCase {

    OrderRepository repository;

    public Long createOrder(OrderDTO orderDTO) throws InvalidOrderException {
        Order order = orderDTO.toOrder();
        if(!order.isValid()) {
            throw new InvalidOrderException();
        }
        order.setStatus(OrderStatus.CREATED);
        return repository.create(order);
    }

    public List<Order> getOrdersByStatus(OrderStatus status) {
        return repository.getOrdersByStatus(status);
    }

}

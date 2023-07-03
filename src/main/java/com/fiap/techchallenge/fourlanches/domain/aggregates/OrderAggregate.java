package com.fiap.techchallenge.fourlanches.domain.aggregates;

import com.fiap.techchallenge.fourlanches.domain.entities.Order;
import com.fiap.techchallenge.fourlanches.domain.exception.InvalidOrderException;
import com.fiap.techchallenge.fourlanches.domain.repositories.OrderRepository;
import com.fiap.techchallenge.fourlanches.domain.valueobjects.OrderVO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderAggregate {

    OrderRepository repository;

    public Long createOrder(OrderVO orderVO) throws InvalidOrderException {
        Order order = orderVO.toOrder();
        if(!order.isValid()) {
            throw new InvalidOrderException();
        }
        return repository.create(order);
    }

}

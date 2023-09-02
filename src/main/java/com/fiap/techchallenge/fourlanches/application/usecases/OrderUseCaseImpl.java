package com.fiap.techchallenge.fourlanches.application.usecases;

import com.fiap.techchallenge.fourlanches.application.dto.OrderDTO;
import com.fiap.techchallenge.fourlanches.domain.entities.Order;
import com.fiap.techchallenge.fourlanches.domain.exception.InvalidOrderException;
import com.fiap.techchallenge.fourlanches.domain.repositories.OrderRepository;
import com.fiap.techchallenge.fourlanches.domain.usecases.OrderStatusUseCase;
import com.fiap.techchallenge.fourlanches.domain.usecases.OrderUseCase;
import com.fiap.techchallenge.fourlanches.domain.valueobjects.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class OrderUseCaseImpl implements OrderUseCase {

    private final OrderRepository repository;
    private final OrderStatusUseCase orderStatusUseCase;

    public List<Order> getAllPendingOrdersOrderedByStatusAndCreatedAt(){
        return repository.getAllOrdersOrderedByStatusAndCreatedAt();
    }

    public Long createOrder(OrderDTO orderDTO) throws InvalidOrderException {
        Order order = orderDTO.toNewOrder();
        orderStatusUseCase.orderCreated(order);
        if(!order.isValid()) {
            throw new InvalidOrderException();
        }
        return repository.create(order);
    }

    public void updateOrderStatus(Long id, OrderStatus orderStatus) {
        Order order = repository.getById(id);
        switch (orderStatus) {
            case CREATED -> orderStatusUseCase.orderCreated(order);
            case RECEIVED -> orderStatusUseCase.orderReceived(order);
            case IN_PREPARATION -> orderStatusUseCase.orderInPreparation(order);
            case READY -> orderStatusUseCase.orderReady(order);
            case FINISHED -> orderStatusUseCase.orderFinished(order);
            case CANCELED -> orderStatusUseCase.orderCanceled(order);
        }
        repository.updateOrderStatus(id, orderStatus);
    }

    public List<Order> getOrdersByStatus(OrderStatus status) {
        return repository.getOrdersByStatus(status);
    }

}

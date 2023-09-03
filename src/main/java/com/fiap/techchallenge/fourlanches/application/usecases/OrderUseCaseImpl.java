package com.fiap.techchallenge.fourlanches.application.usecases;

import com.fiap.techchallenge.fourlanches.application.dto.OrderDTO;
import com.fiap.techchallenge.fourlanches.domain.entities.Order;
import com.fiap.techchallenge.fourlanches.domain.entities.Product;
import com.fiap.techchallenge.fourlanches.domain.exception.InvalidOrderException;
import com.fiap.techchallenge.fourlanches.domain.repositories.OrderRepository;
import com.fiap.techchallenge.fourlanches.domain.usecases.OrderStatusUseCase;
import com.fiap.techchallenge.fourlanches.domain.usecases.OrderUseCase;
import com.fiap.techchallenge.fourlanches.domain.usecases.ProductUseCase;
import com.fiap.techchallenge.fourlanches.domain.valueobjects.OrderItem;
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
    private final ProductUseCase productUseCase;

    public List<Order> getAllPendingOrdersOrderedByStatusAndCreatedAt(){
        return repository.getAllOrdersOrderedByStatusAndCreatedAt();
    }

    public Order createOrder(OrderDTO orderDTO) throws InvalidOrderException {
        Order order = orderDTO.toNewOrder();
        orderStatusUseCase.orderCreated(order);

        if(!order.isValid()) {
            throw new InvalidOrderException();
        }

        for (OrderItem item: order.getOrderItems()) {
            Product product = productUseCase.getProductById(item.getProductId());
            item.setPrice(product.getPrice().doubleValue());
        }

        order.setTotalPrice(order.calculateTotalPrice());

        return repository.createOrder(order);
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

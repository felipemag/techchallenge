package com.fiap.techchallenge.fourlanches.application.usecases;

import com.fiap.techchallenge.fourlanches.application.dto.OrderDTO;
import com.fiap.techchallenge.fourlanches.domain.entities.Order;
import com.fiap.techchallenge.fourlanches.domain.entities.Product;
import com.fiap.techchallenge.fourlanches.domain.exception.InvalidOrderException;
import com.fiap.techchallenge.fourlanches.domain.repositories.OrderRepository;
import com.fiap.techchallenge.fourlanches.domain.usecases.ValidateOrderStatusUseCase;
import com.fiap.techchallenge.fourlanches.domain.usecases.OrderUseCase;
import com.fiap.techchallenge.fourlanches.domain.usecases.ProductUseCase;
import com.fiap.techchallenge.fourlanches.domain.valueobjects.OrderItem;
import com.fiap.techchallenge.fourlanches.domain.valueobjects.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.fiap.techchallenge.fourlanches.domain.valueobjects.OrderStatus.CANCELED;
import static com.fiap.techchallenge.fourlanches.domain.valueobjects.OrderStatus.CREATED;
import static com.fiap.techchallenge.fourlanches.domain.valueobjects.OrderStatus.FINISHED;
import static com.fiap.techchallenge.fourlanches.domain.valueobjects.OrderStatus.IN_PREPARATION;
import static com.fiap.techchallenge.fourlanches.domain.valueobjects.OrderStatus.READY;
import static com.fiap.techchallenge.fourlanches.domain.valueobjects.OrderStatus.RECEIVED;

@Slf4j
@Service
@AllArgsConstructor
public class OrderUseCaseImpl implements OrderUseCase {

    private final OrderRepository repository;
    private final ValidateOrderStatusUseCase validateOrderStatusUseCase;
    private final ProductUseCase productUseCase;

    public List<Order> getAllPendingOrdersOrderedByStatusAndCreatedAt(){
        return repository.getAllOrdersOrderedByStatusAndCreatedAt();
    }

    public Order createOrder(OrderDTO orderDTO) throws InvalidOrderException {
        Order order = orderDTO.toNewOrder();
        delegateOrderStatusValidation(order, OrderDTO.builder().status(CREATED).build());

        setOrderItemPrices(order);

        order.setTotalPrice(order.calculateTotalPrice());

        if(!order.isValid()) {
            throw new InvalidOrderException();
        }

        return repository.createOrder(order);
    }

    public void receiveOrder(Long orderId, boolean paymentApproved) {
        Order order = repository.getById(orderId);
        delegateOrderStatusValidation(order, OrderDTO.builder().status(RECEIVED).build());
        order.setPaymentApproved(paymentApproved);
        repository.updateOrder(orderId, order);
    }

    @Override
    public void orderInPreparation(Long orderId) {
        updateOrderStatus(orderId, IN_PREPARATION);
    }

    @Override
    public void orderReady(Long orderId) {
        updateOrderStatus(orderId, READY);
    }

    @Override
    public void orderFinished(Long orderId) {
        updateOrderStatus(orderId, FINISHED);
    }

    @Override
    public void orderCanceled(Long orderId) {
        updateOrderStatus(orderId, CANCELED);
    }

    public List<Order> getOrdersByStatus(OrderStatus status) {
        return repository.getOrdersByStatus(status);
    }

    public Order getById(Long orderId) { return repository.getById(orderId); }

    private void updateOrderStatus(Long orderId, OrderStatus orderStatus) {
        Order order = repository.getById(orderId);
        delegateOrderStatusValidation(order, OrderDTO.builder().status(orderStatus).build());
        repository.updateOrder(orderId, order);
    }

    private void delegateOrderStatusValidation(Order order, OrderDTO orderDTO) {
        if(!ObjectUtils.isEmpty(orderDTO.getStatus())) {
            switch (orderDTO.getStatus()) {
                case CREATED -> validateOrderStatusUseCase.validateOrderCreated(order);
                case RECEIVED -> validateOrderStatusUseCase.validateOrderReceived(order);
                case IN_PREPARATION -> validateOrderStatusUseCase.validateOrderInPreparation(order);
                case READY -> validateOrderStatusUseCase.validateOrderReady(order);
                case FINISHED -> validateOrderStatusUseCase.validateOrderFinished(order);
                case CANCELED -> validateOrderStatusUseCase.validateOrderCanceled(order);
            }
            order.setStatus(orderDTO.getStatus());
        }
    }

    private void setOrderItemPrices(Order order) {
        for (OrderItem item: order.getOrderItems()) {
            Product product = productUseCase.getProductById(item.getProductId());
            item.setPrice(product.getPrice().doubleValue());
        }
    }
}

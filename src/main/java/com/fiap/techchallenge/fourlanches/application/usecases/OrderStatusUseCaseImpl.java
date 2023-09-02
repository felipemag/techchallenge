package com.fiap.techchallenge.fourlanches.application.usecases;

import com.fiap.techchallenge.fourlanches.application.exception.IncorrectOrderStatusException;
import com.fiap.techchallenge.fourlanches.domain.entities.Order;
import com.fiap.techchallenge.fourlanches.domain.usecases.OrderStatusUseCase;
import com.fiap.techchallenge.fourlanches.domain.valueobjects.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static com.fiap.techchallenge.fourlanches.domain.valueobjects.OrderStatus.CANCELED;
import static com.fiap.techchallenge.fourlanches.domain.valueobjects.OrderStatus.CREATED;
import static com.fiap.techchallenge.fourlanches.domain.valueobjects.OrderStatus.FINISHED;
import static com.fiap.techchallenge.fourlanches.domain.valueobjects.OrderStatus.IN_PREPARATION;
import static com.fiap.techchallenge.fourlanches.domain.valueobjects.OrderStatus.READY;
import static com.fiap.techchallenge.fourlanches.domain.valueobjects.OrderStatus.RECEIVED;

@Slf4j
@Service
@AllArgsConstructor
public class OrderStatusUseCaseImpl implements OrderStatusUseCase {

    private final Map<OrderStatus, List<OrderStatus>> nextStatuses = Map.of(
            CREATED, List.of(RECEIVED, CANCELED),
            RECEIVED, List.of(IN_PREPARATION),
            IN_PREPARATION, List.of(READY),
            READY, List.of(FINISHED),
            FINISHED, Collections.emptyList(),
            CANCELED, Collections.emptyList()
    );

    @Override
    public void orderCreated(Order order) {
        if(ObjectUtils.isEmpty(order.getStatus())) {
            order.setStatus(CREATED);
        } else {
            throw new IncorrectOrderStatusException();
        }
    }

    @Override
    public void orderReceived(Order order) {
        if(isNextStatusPossible(order.getStatus(), RECEIVED)) {
            order.setStatus(RECEIVED);
        } else {
            throw new IncorrectOrderStatusException();
        }
    }

    @Override
    public void orderInPreparation(Order order) {
        if(isNextStatusPossible(order.getStatus(), IN_PREPARATION)) {
            order.setStatus(IN_PREPARATION);
        } else {
            throw new IncorrectOrderStatusException();
        }
    }

    @Override
    public void orderReady(Order order) {
        if(isNextStatusPossible(order.getStatus(), READY)) {
            order.setStatus(READY);
        } else {
            throw new IncorrectOrderStatusException();
        }
    }

    @Override
    public void orderFinished(Order order) {
        if(isNextStatusPossible(order.getStatus(), FINISHED)) {
            order.setStatus(FINISHED);
        } else {
            throw new IncorrectOrderStatusException();
        }
    }

    @Override
    public void orderCanceled(Order order) {
        if(!ObjectUtils.isEmpty(order.getStatus())) {
            order.setStatus(CANCELED);
        } else {
            throw new IncorrectOrderStatusException();
        }
    }

    private boolean isNextStatusPossible(OrderStatus oldStatus, OrderStatus newStatus) {
        return nextStatuses.get(oldStatus).contains(newStatus);
    }

}

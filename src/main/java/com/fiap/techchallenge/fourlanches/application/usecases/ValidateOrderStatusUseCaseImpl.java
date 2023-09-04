package com.fiap.techchallenge.fourlanches.application.usecases;

import com.fiap.techchallenge.fourlanches.application.exception.IncorrectOrderStatusException;
import com.fiap.techchallenge.fourlanches.domain.entities.Order;
import com.fiap.techchallenge.fourlanches.domain.usecases.ValidateOrderStatusUseCase;
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
public class ValidateOrderStatusUseCaseImpl implements ValidateOrderStatusUseCase {

    private final Map<OrderStatus, List<OrderStatus>> nextStatuses = Map.of(
            CREATED, List.of(RECEIVED, CANCELED),
            RECEIVED, List.of(IN_PREPARATION),
            IN_PREPARATION, List.of(READY),
            READY, List.of(FINISHED),
            FINISHED, Collections.emptyList(),
            CANCELED, Collections.emptyList()
    );

    @Override
    public void validateOrderCreated(Order order) {
        if(ObjectUtils.isEmpty(order.getStatus())) {
            order.setStatus(CREATED);
        } else {
            throw new IncorrectOrderStatusException();
        }
    }

    @Override
    public void validateOrderReceived(Order order) {
        validateOrderStatus(order, RECEIVED);
    }

    @Override
    public void validateOrderInPreparation(Order order) {
        validateOrderStatus(order, IN_PREPARATION);
    }

    @Override
    public void validateOrderReady(Order order) {
        validateOrderStatus(order, READY);
    }

    @Override
    public void validateOrderFinished(Order order) {
        validateOrderStatus(order, FINISHED);
    }

    @Override
    public void validateOrderCanceled(Order order) {
        validateOrderStatus(order, CANCELED);
    }

    private void validateOrderStatus(Order order, OrderStatus status) {
        if(isNextStatusPossible(order.getStatus(), status)) {
            order.setStatus(status);
        } else {
            throw new IncorrectOrderStatusException();
        }
    }

    private boolean isNextStatusPossible(OrderStatus oldStatus, OrderStatus newStatus) {
        return nextStatuses.get(oldStatus).contains(newStatus);
    }

}

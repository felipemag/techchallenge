package com.fiap.techchallenge.fourlanches.application.usecases;

import com.fiap.techchallenge.fourlanches.application.PaymentGateway;
import com.fiap.techchallenge.fourlanches.application.dto.OrderDTO;
import com.fiap.techchallenge.fourlanches.domain.entities.Order;
import com.fiap.techchallenge.fourlanches.domain.usecases.CheckoutUseCase;
import com.fiap.techchallenge.fourlanches.domain.usecases.OrderUseCase;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class CheckoutUseCaseImpl implements CheckoutUseCase {

    private final OrderUseCase orderUseCase;
    private final PaymentGateway gateway;

    public Long processPayment(OrderDTO orderDTO){
        Order order = orderUseCase.createOrder(orderDTO);

        gateway.processPayment(order.getId() ,order.getTotalPrice());

        return order.getId();
    }
}

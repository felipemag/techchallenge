package com.fiap.techchallenge.fourlanches.application.usecases;

import com.fiap.techchallenge.fourlanches.domain.entities.Order;
import com.fiap.techchallenge.fourlanches.domain.usecases.OrderUseCase;
import com.fiap.techchallenge.fourlanches.domain.usecases.PaymentUseCase;
import com.fiap.techchallenge.fourlanches.domain.valueobjects.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class PaymentUseCaseImpl implements PaymentUseCase {

    OrderUseCase orderUseCase;

    @Override
    public void approvePayment(Long orderId) {
        orderUseCase.receiveOrder(orderId, true);
    }

    @Override
    public PaymentStatus getPaymentStatusByOrderId(Long id) {
        Order order = orderUseCase.getById(id);
        return PaymentStatus.getPaymentStatus(order.getPaymentApproved());
    }

}

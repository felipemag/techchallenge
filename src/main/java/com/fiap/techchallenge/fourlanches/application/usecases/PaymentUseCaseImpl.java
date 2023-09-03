package com.fiap.techchallenge.fourlanches.application.usecases;

import com.fiap.techchallenge.fourlanches.application.dto.OrderDTO;
import com.fiap.techchallenge.fourlanches.domain.usecases.OrderUseCase;
import com.fiap.techchallenge.fourlanches.domain.usecases.PaymentUseCase;
import com.fiap.techchallenge.fourlanches.domain.valueobjects.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class PaymentUseCaseImpl implements PaymentUseCase {

    OrderUseCase orderUseCase;

    @Override
    public void approvePayment(Long id) {
        orderUseCase.updateOrder(id, OrderDTO.builder()
                .paymentApproved(true)
                .status(OrderStatus.RECEIVED)
                .build());
    }

}

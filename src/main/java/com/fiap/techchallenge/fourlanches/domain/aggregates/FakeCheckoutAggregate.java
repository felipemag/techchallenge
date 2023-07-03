package com.fiap.techchallenge.fourlanches.domain.aggregates;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
@Slf4j
public class FakeCheckoutAggregate {
    public boolean doFakeCheckout(long orderId, BigDecimal totalAmount){
        return true;
    }
}

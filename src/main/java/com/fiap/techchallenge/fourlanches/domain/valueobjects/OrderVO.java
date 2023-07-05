package com.fiap.techchallenge.fourlanches.domain.valueobjects;

import com.fiap.techchallenge.fourlanches.domain.entities.Order;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class OrderVO {

    private List<OrderItem> orderItems;
    private BigDecimal price;
    private Long customerId;

    public Order toOrder() {
        return Order.builder()
                .orderItems(orderItems)
                .totalPrice(price)
                .customerId(customerId)
                .build();
    }
}

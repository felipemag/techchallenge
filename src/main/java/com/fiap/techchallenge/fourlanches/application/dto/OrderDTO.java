package com.fiap.techchallenge.fourlanches.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fiap.techchallenge.fourlanches.domain.entities.Order;
import com.fiap.techchallenge.fourlanches.domain.valueobjects.OrderItem;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class OrderDTO {

    @JsonProperty("items")
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

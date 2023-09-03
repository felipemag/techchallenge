package com.fiap.techchallenge.fourlanches.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fiap.techchallenge.fourlanches.domain.entities.Order;
import com.fiap.techchallenge.fourlanches.domain.valueobjects.OrderItem;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class OrderDTO {

    @JsonProperty("items")
    private List<OrderItem> orderItems;
    private Long customerId;

    public Order toNewOrder() {
        return Order.builder()
                .orderItems(orderItems)
                .customerId(customerId)
                .createdAt(LocalDateTime.now())
                .build();
    }
}

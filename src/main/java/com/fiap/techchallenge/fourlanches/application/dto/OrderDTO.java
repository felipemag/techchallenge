package com.fiap.techchallenge.fourlanches.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fiap.techchallenge.fourlanches.domain.entities.Order;
import com.fiap.techchallenge.fourlanches.domain.valueobjects.OrderItem;
import com.fiap.techchallenge.fourlanches.domain.valueobjects.OrderStatus;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class OrderDTO {

    @JsonProperty("items")
    private List<OrderItem> orderItems;
    private Long customerId;
    private BigDecimal totalPrice;
    private OrderStatus status;
    private Boolean paymentApproved;

    public Order toNewOrder() {
        return Order.builder()
                .orderItems(orderItems)
                .customerId(customerId)
                .totalPrice(totalPrice)
                .status(status)
                .paymentApproved(false)
                .createdAt(LocalDateTime.now())
                .build();
    }
}

package com.fiap.techchallenge.fourlanches.domain.entities;

import com.fiap.techchallenge.fourlanches.domain.valueobjects.OrderItem;
import com.fiap.techchallenge.fourlanches.domain.valueobjects.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class Order {

    private Long id;
    private List<OrderItem> orderItems;
    private Long customerId;
    private BigDecimal totalPrice;
    private OrderStatus status;
    private LocalDateTime createdAt;

    public boolean isValid() {
        return !orderItems.isEmpty();
    }
}

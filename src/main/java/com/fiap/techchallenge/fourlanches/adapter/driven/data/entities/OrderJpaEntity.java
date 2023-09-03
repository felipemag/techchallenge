package com.fiap.techchallenge.fourlanches.adapter.driven.data.entities;

import com.fiap.techchallenge.fourlanches.domain.entities.Order;
import com.fiap.techchallenge.fourlanches.domain.valueobjects.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static com.fiap.techchallenge.fourlanches.adapter.driven.data.entities.OrderItemJpaEntity.fromOrderItem;

@Data
@Entity
@Table(name = "orders")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Long id;
    @OneToMany(fetch= FetchType.LAZY, mappedBy = "order", cascade = CascadeType.PERSIST)
    private List<OrderItemJpaEntity> orderItems;
    @Column(name = "customer_id")
    private Long customerId;
    @Column(name = "total_price")
    private BigDecimal totalPrice;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    private String status;
    @Column(name = "payment_approved")
    private Boolean paymentApproved;

    public Order toOrder() {
        return Order.builder()
                .id(id)
                .totalPrice(totalPrice)
                .status(OrderStatus.valueOf(status))
                .orderItems(orderItems.stream().map(OrderItemJpaEntity::toOrderItem).toList())
                .customerId(customerId)
                .createdAt(createdAt)
                .paymentApproved(paymentApproved)
                .build();
    }

    public static OrderJpaEntity fromOrder(Order order) {
        OrderJpaEntity orderJpaEntity = OrderJpaEntity.builder()
                .totalPrice(order.getTotalPrice())
                .customerId(order.getCustomerId())
                .status(order.getStatus().toString())
                .paymentApproved(order.getPaymentApproved())
                .createdAt(order.getCreatedAt())
                .build();

        orderJpaEntity.setOrderItems(order.getOrderItems().stream()
                        .map(orderItem -> fromOrderItem(orderItem, orderJpaEntity)).toList());
        return orderJpaEntity;
    }
}

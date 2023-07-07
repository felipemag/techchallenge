package com.fiap.techchallenge.fourlanches.adapter.driven.data.entities;

import com.fiap.techchallenge.fourlanches.domain.entities.Order;
import com.fiap.techchallenge.fourlanches.domain.valueobjects.OrderStatus;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
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
    private String status;

    public Order toOrder() {
        return Order.builder()
                .id(id)
                .totalPrice(totalPrice)
                .status(OrderStatus.valueOf(status))
                .orderItems(orderItems.stream().map(OrderItemJpaEntity::toOrderItem).toList())
                .customerId(customerId)
                .build();
    }

    public static OrderJpaEntity fromOrder(Order order) {
        OrderJpaEntity orderJpaEntity = OrderJpaEntity.builder()
                .totalPrice(order.getTotalPrice())
                .customerId(order.getCustomerId())
                .status(order.getStatus().toString())
                .build();
        orderJpaEntity.setOrderItems(order.getOrderItems().stream()
                        .map(orderItem -> fromOrderItem(orderItem, orderJpaEntity)).toList());
        return orderJpaEntity;
    }
}

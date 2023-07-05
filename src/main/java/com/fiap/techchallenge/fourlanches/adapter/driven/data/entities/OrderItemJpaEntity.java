package com.fiap.techchallenge.fourlanches.adapter.driven.data.entities;

import com.fiap.techchallenge.fourlanches.domain.valueobjects.OrderItem;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "order_items")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "order_id", nullable = false)
    private OrderJpaEntity order;
    @Column(name = "product_id")
    private Long productId;
    private int quantity;
    private BigDecimal price;
    private String observation;

    public OrderItem toOrderItem() {
        return OrderItem.builder()
                .productId(productId)
                .quantity(quantity)
                .price(price)
                .observation(observation)
                .build();
    }

    public static OrderItemJpaEntity fromOrderItem(OrderItem orderItem, OrderJpaEntity order) {
        return OrderItemJpaEntity.builder()
                .order(order)
                .productId(orderItem.getProductId())
                .quantity(orderItem.getQuantity())
                .price(orderItem.getPrice())
                .observation(orderItem.getObservation())
                .build();
    }
}

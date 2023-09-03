package com.fiap.techchallenge.fourlanches.adapter.driven.data.repositories;

import com.fiap.techchallenge.fourlanches.adapter.driven.data.OrderJpaRepository;
import com.fiap.techchallenge.fourlanches.adapter.driven.data.entities.OrderJpaEntity;
import com.fiap.techchallenge.fourlanches.domain.entities.Order;
import com.fiap.techchallenge.fourlanches.domain.repositories.OrderRepository;
import com.fiap.techchallenge.fourlanches.domain.valueobjects.OrderStatus;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class OrderRepositoryImpl implements OrderRepository {

    private OrderJpaRepository jpaRepository;

    @Override
    public Order createOrder(Order order) {
        OrderJpaEntity orderJpaEntity = jpaRepository.save(OrderJpaEntity.fromOrder(order));
        return orderJpaEntity.toOrder();
    }

    @Override
    public Order getById(Long id) {
        OrderJpaEntity orderJpaEntity = jpaRepository.getReferenceById(id);
        return orderJpaEntity.toOrder();
    }

    @Override
    public boolean save(Order order) {
        OrderJpaEntity orderJpaEntity = OrderJpaEntity.fromOrder(order);
        jpaRepository.save(orderJpaEntity);
        return true;
    }

    @Override
    public List<Order> getOrdersByStatus(OrderStatus status) {
        return jpaRepository.findByStatus(status.toString()).stream().map(OrderJpaEntity::toOrder).toList();
    }

    @Override
    public List<Order> getAllOrdersOrderedByStatusAndCreatedAt() {
        return jpaRepository.getAllPendingOrdersOrderedByStatusAndCreatedAt().stream().map(OrderJpaEntity::toOrder).toList();
    }

    @Override
    public void updateOrder(Long id, Order order) {
        OrderJpaEntity orderJpaEntity = jpaRepository.getReferenceById(id);
        orderJpaEntity.setStatus(order.getStatus().name());
        orderJpaEntity.setPaymentApproved(order.getPaymentApproved());
        jpaRepository.save(orderJpaEntity);
    }

}

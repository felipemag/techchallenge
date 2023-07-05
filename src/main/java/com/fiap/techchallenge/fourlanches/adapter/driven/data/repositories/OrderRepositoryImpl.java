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
    public Long create(Order order) {

        OrderJpaEntity orderJpaEntity = jpaRepository.save(OrderJpaEntity.fromOrder(order));

        return orderJpaEntity.getId();
    }

    @Override
    public List<Order> getOrdersByStatus(OrderStatus status) {
        return jpaRepository.findByStatus(status.toString()).stream().map(OrderJpaEntity::toOrder).toList();
    }

}

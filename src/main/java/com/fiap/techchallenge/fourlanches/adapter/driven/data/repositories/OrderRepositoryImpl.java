package com.fiap.techchallenge.fourlanches.adapter.driven.data.repositories;

import com.fiap.techchallenge.fourlanches.adapter.driven.data.OrderJpaRepository;
import com.fiap.techchallenge.fourlanches.adapter.driven.data.entities.OrderJpaEntity;
import com.fiap.techchallenge.fourlanches.domain.entities.Order;
import com.fiap.techchallenge.fourlanches.domain.repositories.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class OrderRepositoryImpl implements OrderRepository {

    private OrderJpaRepository jpaRepository;

    @Override
    public Long create(Order order) {

        OrderJpaEntity orderJpaEntity = jpaRepository.save(OrderJpaEntity.fromOrder(order));

        return orderJpaEntity.getId();
    }

}

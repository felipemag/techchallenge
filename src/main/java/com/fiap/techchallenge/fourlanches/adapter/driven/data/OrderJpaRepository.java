package com.fiap.techchallenge.fourlanches.adapter.driven.data;

import com.fiap.techchallenge.fourlanches.adapter.driven.data.entities.OrderJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderJpaRepository extends JpaRepository<OrderJpaEntity, Long> {

}

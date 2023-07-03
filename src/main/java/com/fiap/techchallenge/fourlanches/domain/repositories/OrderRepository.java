package com.fiap.techchallenge.fourlanches.domain.repositories;

import com.fiap.techchallenge.fourlanches.domain.entities.Order;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository {

    Long create(Order order);

}

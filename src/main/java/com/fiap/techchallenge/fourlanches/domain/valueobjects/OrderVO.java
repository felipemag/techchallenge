package com.fiap.techchallenge.fourlanches.domain.valueobjects;

import com.fiap.techchallenge.fourlanches.domain.entities.Order;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderVO {

    public Order toOrder() {
        return Order.builder().build();
    }
}

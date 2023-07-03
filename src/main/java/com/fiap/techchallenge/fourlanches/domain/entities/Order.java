package com.fiap.techchallenge.fourlanches.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
public class Order {

    private Long id;
    private BigDecimal price;

    public boolean isValid() {
        return true;
    }
}

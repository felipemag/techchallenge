package com.fiap.techchallenge.fourlanches.domain.valueobjects;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class OrderItem {

    private Long productId;
    private int quantity;
    private BigDecimal price;
    private String observation;

}

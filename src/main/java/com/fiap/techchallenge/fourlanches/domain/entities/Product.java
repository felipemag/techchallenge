package com.fiap.techchallenge.fourlanches.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
public class Product {

    private Long id;
    private Category category;
    private String name;
    private String description;
    private BigDecimal price;
    private boolean isAvailable;

}

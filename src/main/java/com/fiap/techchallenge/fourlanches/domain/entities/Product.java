package com.fiap.techchallenge.fourlanches.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

import static com.fiap.techchallenge.fourlanches.domain.AssertationConcern.isNotEmpty;
import static com.fiap.techchallenge.fourlanches.domain.AssertationConcern.isPositive;

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

    public boolean isValid() {
        return  isNotEmpty(category.toString())
            && isNotEmpty(name)
            && isNotEmpty(description)
            && isPositive(price)
            && isAvailable;
    }
}

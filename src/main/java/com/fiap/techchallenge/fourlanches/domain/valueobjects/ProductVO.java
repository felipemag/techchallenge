package com.fiap.techchallenge.fourlanches.domain.valueobjects;

import com.fiap.techchallenge.fourlanches.domain.entities.Category;
import com.fiap.techchallenge.fourlanches.domain.entities.Product;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ProductVO {

    private Category category;
    private String name;
    private String description;
    private BigDecimal price;
    private boolean isAvailable;

    public Product toProduct() {
        return Product.builder()
                .category(category)
                .name(name)
                .description(description)
                .price(price)
                .isAvailable(isAvailable)
                .build();
    }
}

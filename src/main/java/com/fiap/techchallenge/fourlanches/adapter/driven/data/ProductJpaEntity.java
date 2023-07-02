package com.fiap.techchallenge.fourlanches.adapter.driven.data;

import com.fiap.techchallenge.fourlanches.domain.entities.Category;
import com.fiap.techchallenge.fourlanches.domain.entities.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "products")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Long id;
    private String name;
    private String description;
    private String category;
    private BigDecimal price;
    @Column(name = "is_available")
    private Boolean isAvailable;

    public Product toProduct() {
        return Product.builder()
                .id(id)
                .name(name)
                .description(description)
                .category(Category.valueOf(category))
                .price(price)
                .isAvailable(isAvailable)
                .build();
    }

    public static ProductJpaEntity fromProduct(Product product) {
        return ProductJpaEntity.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .category(product.getCategory().toString())
                .price(product.getPrice())
                .isAvailable(product.isAvailable())
                .build();
    }
}

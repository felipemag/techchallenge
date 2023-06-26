package com.fiap.techchallenge.fourlanches.adapter.driven.data;

import com.fiap.techchallenge.fourlanches.domain.entities.Product;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Entity
@Builder
@AllArgsConstructor
public class ProductJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Product getProductEntity() {
        return Product.builder()
                .id(id.toString())
                .build();
    }
}

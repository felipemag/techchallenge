package com.fiap.techchallenge.fourlanches.adapter.driven.data;

import com.fiap.techchallenge.fourlanches.domain.entities.Product;
import com.fiap.techchallenge.fourlanches.domain.exception.ProductNotFoundException;
import com.fiap.techchallenge.fourlanches.domain.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {

    private ProductJpaRepository jpaRepository;

    public Product getProductById(String id) {
        return jpaRepository.findById(Long.getLong(id))
                .orElseThrow(ProductNotFoundException::new).toProduct();
    }

    public List<Product> getProducts() {
        return jpaRepository.findAll().stream().map(ProductJpaEntity::toProduct).collect(Collectors.toList());
    }

    @Override
    public Long create(Product product) {
        ProductJpaEntity productJpaEntity = jpaRepository.save(ProductJpaEntity.fromProduct(product));
        return productJpaEntity.getId();
    }

}

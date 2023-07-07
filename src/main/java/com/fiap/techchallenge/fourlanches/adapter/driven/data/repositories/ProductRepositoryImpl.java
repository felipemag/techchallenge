package com.fiap.techchallenge.fourlanches.adapter.driven.data.repositories;

import com.fiap.techchallenge.fourlanches.adapter.driven.data.entities.ProductJpaEntity;
import com.fiap.techchallenge.fourlanches.adapter.driven.data.ProductJpaRepository;
import com.fiap.techchallenge.fourlanches.domain.entities.Category;
import com.fiap.techchallenge.fourlanches.domain.entities.Product;
import com.fiap.techchallenge.fourlanches.application.exception.ProductNotFoundException;
import com.fiap.techchallenge.fourlanches.domain.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {

    private ProductJpaRepository jpaRepository;

    public Product getProductById(Long id) {
        return jpaRepository.findById(id)
                .orElseThrow(ProductNotFoundException::new).toProduct();
    }

    public List<Product> getProducts() {
        return jpaRepository.findAll().stream().map(ProductJpaEntity::toProduct).collect(Collectors.toList());
    }
    public List<Product> getProductsByCategory(Category category) {
        return jpaRepository.findByCategory(category.toString()).stream().map(ProductJpaEntity::toProduct).collect(Collectors.toList());
    }

    public void deleteProduct(Long id) {
        jpaRepository.deleteById(id);
    }

    public void updateProduct(Product product) {
        jpaRepository.save(ProductJpaEntity.fromProduct(product));
    }

    @Override
    public Long create(Product product) {
        ProductJpaEntity productJpaEntity = jpaRepository.save(ProductJpaEntity.fromProduct(product));
        return productJpaEntity.getId();
    }


}

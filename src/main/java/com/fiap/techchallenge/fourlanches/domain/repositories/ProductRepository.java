package com.fiap.techchallenge.fourlanches.domain.repositories;

import com.fiap.techchallenge.fourlanches.domain.entities.Category;
import com.fiap.techchallenge.fourlanches.domain.entities.Product;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository {

    Product getProductById(Long id);

    List<Product> getProducts();

    List<Product> getProductsByCategory(Category category);

    Long create(Product product);

    void deleteProduct(Long id);

    void updateProduct(Product product);
}

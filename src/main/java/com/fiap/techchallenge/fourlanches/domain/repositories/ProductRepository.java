package com.fiap.techchallenge.fourlanches.domain.repositories;

import com.fiap.techchallenge.fourlanches.domain.entities.Product;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository {

    Product getProductById(String id);

    List<Product> getProducts();

}

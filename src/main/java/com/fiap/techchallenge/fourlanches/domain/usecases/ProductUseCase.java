package com.fiap.techchallenge.fourlanches.domain.usecases;

import com.fiap.techchallenge.fourlanches.application.dto.ProductDTO;
import com.fiap.techchallenge.fourlanches.domain.entities.Category;
import com.fiap.techchallenge.fourlanches.domain.entities.Product;

import java.util.List;

public interface ProductUseCase {
    Product getProductById(Long id);
    List<Product> getProducts();
    List<Product> getProductsByCategory(Category category);
    Long createProduct(ProductDTO productDTO);
    void updateProduct(Long id, ProductDTO productDTO);
    void deleteProduct(Long id);
}

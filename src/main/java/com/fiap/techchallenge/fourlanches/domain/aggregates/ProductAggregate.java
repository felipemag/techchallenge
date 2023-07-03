package com.fiap.techchallenge.fourlanches.domain.aggregates;

import com.fiap.techchallenge.fourlanches.domain.entities.Category;
import com.fiap.techchallenge.fourlanches.domain.entities.Product;
import com.fiap.techchallenge.fourlanches.domain.exception.InvalidProductException;
import com.fiap.techchallenge.fourlanches.domain.repositories.ProductRepository;
import com.fiap.techchallenge.fourlanches.domain.valueobjects.ProductVO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductAggregate {

    ProductRepository productRepository;

    public Product getProductById(String id) {
        return productRepository.getProductById(id);
    }

    public List<Product> getProducts() {
        return productRepository.getProducts();
    }

    public List<Product> getProductsByCategory(Category category) {
        return productRepository.getProductsByCategory(category);
    }

    public Long createProduct(ProductVO productVO) throws InvalidProductException {
        Product product = productVO.toProduct();
        if(!product.isValid()) {
            throw new InvalidProductException();
        }
        return productRepository.create(product);
    }

    public void deleteProduct(String id) {
         productRepository.deleteProduct(id);
    }

}

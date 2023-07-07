package com.fiap.techchallenge.fourlanches.application.usecases;

import com.fiap.techchallenge.fourlanches.domain.entities.Category;
import com.fiap.techchallenge.fourlanches.domain.entities.Product;
import com.fiap.techchallenge.fourlanches.domain.exception.InvalidProductException;
import com.fiap.techchallenge.fourlanches.domain.repositories.ProductRepository;
import com.fiap.techchallenge.fourlanches.application.dto.ProductDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductUseCase {

    ProductRepository productRepository;

    public Product getProductById(Long id) {
        return productRepository.getProductById(id);
    }

    public List<Product> getProducts() {
        return productRepository.getProducts();
    }

    public List<Product> getProductsByCategory(Category category) {
        return productRepository.getProductsByCategory(category);
    }

    public Long createProduct(ProductDTO productDTO) throws InvalidProductException {
        Product product = productDTO.toProduct();
        if(!product.isValid()) {
            throw new InvalidProductException();
        }
        return productRepository.create(product);
    }

    public void updateProduct(Long id, ProductDTO productDTO) throws InvalidProductException {
        Product product = productDTO.toProduct();
        if(!product.isValid()) {
            throw new InvalidProductException();
        }
        productRepository.getProductById(id);
        product.setId(id);
        productRepository.updateProduct(product);
    }

    public void deleteProduct(Long id) {
         productRepository.deleteProduct(id);
    }

}

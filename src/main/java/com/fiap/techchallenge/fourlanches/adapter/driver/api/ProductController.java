package com.fiap.techchallenge.fourlanches.adapter.driver.api;

import com.fiap.techchallenge.fourlanches.domain.aggregates.ProductAggregate;
import com.fiap.techchallenge.fourlanches.domain.entities.Product;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("products")
public class ProductController {

    private ProductAggregate productAggregate;

    @GetMapping(value = "/{id}", produces = "application/json")
    public Product getProduct(@PathVariable String id) {
        return productAggregate.getProductById(id);
    }

    @GetMapping(value = "/", produces = "application/json")
    public List<Product> getProducts() {
        return productAggregate.getProducts();
    }

}

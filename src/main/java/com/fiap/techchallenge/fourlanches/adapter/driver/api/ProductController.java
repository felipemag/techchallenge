package com.fiap.techchallenge.fourlanches.adapter.driver.api;

import com.fiap.techchallenge.fourlanches.domain.aggregates.ProductAggregate;
import com.fiap.techchallenge.fourlanches.domain.entities.Product;
import com.fiap.techchallenge.fourlanches.domain.exception.InvalidProductException;
import com.fiap.techchallenge.fourlanches.domain.valueobjects.ProductVO;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@ControllerAdvice(assignableTypes = ProductControllerAdvisor.class)
@RequestMapping("products")
public class ProductController {

    private ProductAggregate productAggregate;

    @GetMapping(value = "/{id}", produces = "application/json")
    @ApiResponse(responseCode = "200")
    public Product getProduct(@PathVariable String id) {
        return productAggregate.getProductById(id);
    }

    @GetMapping(value = "/", produces = "application/json")
    @ApiResponse(responseCode = "200")
    public List<Product> getProducts() {
        return productAggregate.getProducts();
    }

    @PostMapping(value = "/", produces = "application/json")
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createProduct(@RequestBody ProductVO productVO) throws InvalidProductException {
        Long returnedId = productAggregate.createProduct(productVO);
        return ResponseEntity.status(HttpStatus.CREATED).body(returnedId);
    }

}

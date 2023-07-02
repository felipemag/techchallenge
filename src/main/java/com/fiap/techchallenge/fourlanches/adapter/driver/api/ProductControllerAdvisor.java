package com.fiap.techchallenge.fourlanches.adapter.driver.api;

import com.fiap.techchallenge.fourlanches.domain.entities.ApiErrorMessage;
import com.fiap.techchallenge.fourlanches.domain.exception.CustomerNotFoundException;
import com.fiap.techchallenge.fourlanches.domain.exception.CustomerSaveException;
import com.fiap.techchallenge.fourlanches.domain.exception.InvalidProductException;
import com.fiap.techchallenge.fourlanches.domain.exception.ProductNotFoundException;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ProductControllerAdvisor {
    @ExceptionHandler(ProductNotFoundException.class)
    @ApiResponse(responseCode = "404")
    public ResponseEntity<ApiErrorMessage> handleProductNotFoundException() {
       var errorMessage = new ApiErrorMessage(HttpStatus.NOT_FOUND, "Product not found");

        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidProductException.class)
    @ApiResponse(responseCode = "400")
    public ResponseEntity<ApiErrorMessage> handleInvalidProductException() {
        var errorMessage = new ApiErrorMessage(HttpStatus.BAD_REQUEST, "Invalid product");

        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

}

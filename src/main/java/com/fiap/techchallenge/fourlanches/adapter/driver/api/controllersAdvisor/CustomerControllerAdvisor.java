package com.fiap.techchallenge.fourlanches.adapter.driver.api.controllersAdvisor;

import com.fiap.techchallenge.fourlanches.application.exception.CustomerNotFoundException;
import com.fiap.techchallenge.fourlanches.application.exception.CustomerSaveException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class CustomerControllerAdvisor {

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ApiErrorMessage> handleCustomerNotFoundException(
            CustomerNotFoundException ex, WebRequest request) {

        var errorMessage = new ApiErrorMessage(HttpStatus.NOT_FOUND, "customer not found");

        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CustomerSaveException.class)
    public ResponseEntity<ApiErrorMessage> handleCustomerSaveException(
            CustomerSaveException ex, WebRequest request) {

        var errorMessage = new ApiErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, "could not save customer");

        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

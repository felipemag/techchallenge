package com.fiap.techchallenge.fourlanches.adapter.driver.api.controllersAdvisor;

import com.fiap.techchallenge.fourlanches.application.exception.IncorrectOrderStatusException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class OrderControllerAdvisor {

    @ExceptionHandler(IncorrectOrderStatusException.class)
    public ResponseEntity<ApiErrorMessage> handleIncorrectOrderStatusException() {

        var errorMessage = new ApiErrorMessage(HttpStatus.BAD_REQUEST, "Order could not be updated");

        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

}

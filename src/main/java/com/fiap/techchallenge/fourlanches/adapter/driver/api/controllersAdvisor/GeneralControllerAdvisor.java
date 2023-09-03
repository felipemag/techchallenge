package com.fiap.techchallenge.fourlanches.adapter.driver.api.controllersAdvisor;

import com.fiap.techchallenge.fourlanches.application.exception.CustomerSaveException;
import com.fiap.techchallenge.fourlanches.adapter.driver.api.exception.InternalServerError;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GeneralControllerAdvisor {

    @ExceptionHandler(InternalServerError.class)
    public ResponseEntity<ApiErrorMessage> handleInternalServerErrorException(
            CustomerSaveException ex, WebRequest request) {

        var errorMessage = new ApiErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, "an error happened");


        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiErrorMessage> handleEntityNotFoundException(
            EntityNotFoundException ex, WebRequest request) {

        var errorMessage = new ApiErrorMessage(HttpStatus.NOT_FOUND, "resource not found");


        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }
}

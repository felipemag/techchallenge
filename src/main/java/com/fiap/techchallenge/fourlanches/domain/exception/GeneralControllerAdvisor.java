package com.fiap.techchallenge.fourlanches.domain.exception;

import com.fiap.techchallenge.fourlanches.domain.entities.ApiErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GeneralControllerAdvisor {

    @ExceptionHandler(InternalServerError.class)
    public ResponseEntity<Object> handleNodataFoundException(
            CustomerSaveException ex, WebRequest request) {

        var errorMessage = new ApiErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, "an error happened");


        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

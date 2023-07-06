package com.fiap.techchallenge.fourlanches.application.exception;

import lombok.Data;

@Data
public class CustomerNotFoundException  extends RuntimeException {
    public CustomerNotFoundException() {
    }

    public CustomerNotFoundException(Exception cause) {
        super(cause);
    }

    public CustomerNotFoundException(String message) {
        super(message);
    }

    public CustomerNotFoundException(String message, Exception cause) {
        super(message, cause);
    }
}

package com.fiap.techchallenge.fourlanches.domain.exception;

import jakarta.persistence.EntityNotFoundException;
import lombok.Data;

@Data

public class CustomerNotFoundException extends EntityNotFoundException {
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

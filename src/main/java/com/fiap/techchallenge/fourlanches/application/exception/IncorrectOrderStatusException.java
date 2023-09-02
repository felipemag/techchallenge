package com.fiap.techchallenge.fourlanches.application.exception;

import lombok.Data;

@Data
public class IncorrectOrderStatusException extends RuntimeException {

    private String orderId;

}
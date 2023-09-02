package com.fiap.techchallenge.fourlanches.application.exception;

import lombok.Data;

@Data
public class ProductNotFoundException extends RuntimeException {

    private String productId;

}

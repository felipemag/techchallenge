package com.fiap.techchallenge.fourlanches.domain.exception;

import lombok.Data;

@Data
public class ProductNotFoundException extends RuntimeException {

    private String productId;


}

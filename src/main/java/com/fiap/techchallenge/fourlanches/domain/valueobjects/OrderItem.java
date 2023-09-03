package com.fiap.techchallenge.fourlanches.domain.valueobjects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderItem {

    private Long productId;
    private int quantity;
    @JsonIgnore
    private double price;
    private String observation;

}

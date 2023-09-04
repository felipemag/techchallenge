package com.fiap.techchallenge.fourlanches.adapter.driver.api.controllers;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fiap.techchallenge.fourlanches.domain.valueobjects.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class PaymentStatusResponse {

    @JsonProperty("order_id")
    private final Long orderId;
    private PaymentStatus status;

}

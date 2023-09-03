package com.fiap.techchallenge.fourlanches.domain.entities;

import com.fiap.techchallenge.fourlanches.domain.valueobjects.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class PaymentState {
    private PaymentStatus status;
}
